package com.example.warsztatsamochodowy.controller;

import com.example.warsztatsamochodowy.dto.OrderDto;
import com.example.warsztatsamochodowy.dto.OrderStatus;
import com.example.warsztatsamochodowy.entity.*;
import com.example.warsztatsamochodowy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Kontroler odpowiedzialny za obsługę zamówień
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final CarService carService;
    private final WorkerService workerService;
    private final StorageService storageService;
    private final ProductService productService;

    /**
     * Instantiates a new Order controller.
     * Do kontroler wstrzyknięte są zależności OrderService, CarService, WorkerService, StorageService, Product Service
     * Wstrzyknięcie tych zależności ma na celu umożliwić dostęp do powyższych usług
     *
     * @param orderService   the order service
     * @param carService     the car service
     * @param workerService  the worker service
     * @param storageService the storage service
     * @param productService the product service
     */
    @Autowired
    public OrderController(
            OrderService orderService,
            CarService carService,
            WorkerService workerService,
            StorageService storageService,
            ProductService productService
    ) {
        this.orderService = orderService;
        this.carService = carService;
        this.workerService = workerService;
        this.storageService = storageService;
        this.productService = productService;
    }

    /**
     * Metoda która zwraca listę zamówień
     *
     * Metoda pobiera parametry Model i Authentication i zwraca string(odnośnik do widoku z listą zamówień).
     *
     * @param model          the model
     * @param authentication Obsługa autoryzacji użytkowników
     * @return string(odnośnik do widoku order / list).
     */
    @GetMapping("/list")
    public String list(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Iterable<Order> list;
        if (user.hasRole(Role.ROLE_ADMIN)) {
            list = orderService.getList();
        } else if (user.hasRole(Role.ROLE_WORKER)) {
            list = orderService.getListByWorker((Worker) user);
        } else {
            list = orderService.getListByCustomer(user);
        }

        model.addAttribute("orders", list);
        return "order/list";
    }

    /**
     * Obsługa okna szczegółów zamówienia
     *
     * @param model          the model
     * @param id             the order id
     * @param authentication the authentication
     * @return the string
     */
    @GetMapping("/details/{id}")
    public String details(Model model, @PathVariable long id, Authentication authentication) {
        Optional<Order> opt = orderService.getOne(id);
        if (opt.isEmpty()) return "redirect:/";
        Order order = opt.get();

        User user = (User) authentication.getPrincipal();
        if ((user instanceof Worker && !order.getPracownicy().contains((Worker) user))
                && !user.hasRole("ROLE_ADMIN")
                && order.getCar().getOwner() != user) {
            return "redirect:/";
        }

        Iterable<Product> products = order.getServices().stream()
                .map(Service::getProduct)
                .filter(Objects::nonNull)
                .toList();

        model.addAttribute("order", order);
        model.addAttribute("products", products);
        return "order/details";
    }

    /**
     * Metoda obsługująca tworzenie nowego zamówienia
     *
     * @param model          the model
     * @param authentication the authentication
     * @return the string
     */
    @GetMapping("/new")
    public String newOrder(Model model, Authentication authentication) {
        OrderDto dto = new OrderDto();
        model.addAttribute("order", dto);
        loadFormData(model, authentication);
        return "order/form";
    }

    /**
     * Metoda obsługująca edycję danych zamówienia
     *
     * @param model          the model
     * @param id             the order id
     * @param authentication the authentication
     * @return the string
     */
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id, Authentication authentication) {
        Optional<Order> opt = orderService.getOne(id);
        if (opt.isEmpty()) return "redirect:/";
        model.addAttribute("order", new OrderDto(opt.get()));
        loadFormData(model, authentication);
        return "order/form";
    }

    /**
     * Metoda obsługująca zapis danych zamówienia
     *
     * @param dto            the dto
     * @param model          the model
     * @param authentication the authentication
     * @return the string
     */
    @PostMapping("/save")
    public String save(@ModelAttribute OrderDto dto, Model model, Authentication authentication) {
        List<String> errors = orderService.validate(dto);
        if (!errors.isEmpty()) {
            model.addAttribute("errorMsg", errors);
            model.addAttribute("order", dto);
            loadFormData(model, authentication);
            return "order/form";
        }

        long result = orderService.save(dto);
        return "redirect:/order/details/" + result;
    }

    /**
     * Obsługa pobierania faktury która jest załączona do zamówienia
     *
     * @param id order Id
     * @return the response entity
     */
    @GetMapping("/invoice/{id}")
    @ResponseBody
    public ResponseEntity<Resource> invoice(@PathVariable long id) {
        Optional<Order> opt = orderService.getOne(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Resource file = storageService.load(id, opt.get().getFaktura());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    /**
     * Metoda przekazuje do widoku order/form dane statusów, samochodów i pracowników
     *
     * @param model          the model
     * @param authentication the authentication
     */
    private void loadFormData(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Iterable<Car> list;
        if (user.hasRole(Role.ROLE_ADMIN) || user.hasRole(Role.ROLE_WORKER)) {
            list = carService.getAll();
        } else {
            list = carService.getListByCustomer(user);
        }

        model.addAttribute("statuses", OrderStatus.values());
        model.addAttribute("cars", list);
        model.addAttribute("workers", workerService.getAll());
    }
}
