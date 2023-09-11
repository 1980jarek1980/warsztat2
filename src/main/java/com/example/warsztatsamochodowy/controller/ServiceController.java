package com.example.warsztatsamochodowy.controller;

import com.example.warsztatsamochodowy.dto.ServiceDto;
import com.example.warsztatsamochodowy.entity.Order;
import com.example.warsztatsamochodowy.entity.Service;
import com.example.warsztatsamochodowy.service.OrderService;
import com.example.warsztatsamochodowy.service.ProductService;
import com.example.warsztatsamochodowy.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

/**
 * Kontroler odpowiedzialny za obsługę usług
 */
@Controller
@RequestMapping("/service")
public class ServiceController {

    private final ServicesService servicesService;
    private final OrderService orderService;
    private final ProductService productService;

    /**
     * Instantiates a new Service controller.
     * Do kontroler wstrzyknięte są zależności ServicesService, OrderService, Product Service
     * Wstrzyknięcie tych zależności ma na celu umożliwić dostęp do powyższych usług
     *
     * @param servicesService the services service
     * @param orderService    the order service
     * @param productService  the product service
     */
    @Autowired
    public ServiceController(ServicesService servicesService, OrderService orderService, ProductService productService) {
        this.servicesService = servicesService;
        this.orderService = orderService;
        this.productService = productService;
    }

    /**
     * Strona główna wyświetla listę usług
     *
     * @param model the model
     * @return Odnośnik do widoku listy usług
     */
    @GetMapping("/list")
    public String index(Model model) {
        Iterable<ServiceDto> list = servicesService.getAllDto();
        model.addAttribute("services", list);
        return "service/list";
    }

    /**
     * Metoda obsługująca tworzenie nowej usługi (powiązanej z zamówieniem)
     *
     * @param model the model
     * @param id    the id
     * @return Odnośnik do widoku reprezentującego formularz tworzenia nowej ysługi
     */
    @GetMapping("/new/{id}")
    public String newService(Model model, @PathVariable long id) {
        Optional<Order> opt = orderService.getOne(id);
        if (opt.isEmpty()) return "redirect:/";
        model.addAttribute("service", new ServiceDto(opt.get()));
        model.addAttribute("products", productService.getList());
        return "service/form";
    }

    /**
     * Metoda obsługująca edycję danych usługi (powiązanej z zamówieniem)
     *
     * @param id    the id
     * @param model the model
     * @return Odnośnik do widoku reprezentującego formularz edycji usługi
     */
    @GetMapping("/edit/{id}")
    public String editService(@PathVariable long id, Model model) {
        Optional<Service> opt = servicesService.getOne(id);
        if (opt.isEmpty()) return "redirect:/";
        ServiceDto s = new ServiceDto(opt.get());
        if(s.getProduct() != null){
            s.setCena(s.getCena() - (s.getCount() * s.getProduct().getCena()));
        }
        model.addAttribute("service", s);
        model.addAttribute("products", productService.getList());
        return "service/form";
    }

    /**
     * Metoda obsługująca zapis danych usługi (powiązanej z zamówieniem)
     *
     * @param dto   the dto
     * @param model the model
     * @return String Odniesienie do widoku
     */
    @PostMapping("/save")
    public String save(@ModelAttribute ServiceDto dto, Model model) {
        List<String> errors = servicesService.validate(dto);
        if (!errors.isEmpty()) {
            model.addAttribute("errorMsg", errors);
            model.addAttribute("service", dto);
            return "service/form";
        }

        servicesService.save(dto);
        return "redirect:/order/details/" + dto.getOrder().getId();
    }

    /**
     * Metoda odpowiedzialna za usuwanie danych usługi (powiązanej z zamówieniem)
     *
     * @param id         the id
     * @param attributes the attributes
     * @return the redirect view
     */
    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable long id, RedirectAttributes attributes) {
        Optional<Service> opt = servicesService.getOne(id);
        if (opt.isEmpty()) return new RedirectView("/");

        Service service = opt.get();
        long orderId = service.getOrder().getId();
        servicesService.delete(service);

        attributes.addAttribute("successAlert", "Usunięto.");
        return new RedirectView("/order/details/" + orderId);
    }
}
