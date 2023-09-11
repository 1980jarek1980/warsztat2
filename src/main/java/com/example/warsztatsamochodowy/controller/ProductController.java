package com.example.warsztatsamochodowy.controller;

import com.example.warsztatsamochodowy.dto.ProductDto;
import com.example.warsztatsamochodowy.entity.Product;
import com.example.warsztatsamochodowy.service.CarService;
import com.example.warsztatsamochodowy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

/**
 * Kontroler odpowiedzialny za obsługę produktów
 */
@Controller
public class ProductController {

    private final ProductService productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Strona główna wyświetla listę produktów
     *
     * @param model the model
     * @return Odnośnik do widoku listy produktów
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("list", productService.getList());
        return "product/list";
    }

    /**
     * Obsługa okna szczegółów produktu
     *
     * @param model the model
     * @param id    the id
     * @return Odnośnik do widoku reprezentującego szczegóły produktu
     */
    @GetMapping("/product/details/{id}")
    public String details(Model model, @PathVariable long id) {

        Optional<Product> opt = productService.getOne(id);

        if (opt.isEmpty()) return "redirect:/";

        model.addAttribute("p", opt.get());
        return "product/details";
    }

    /**
     * Metoda obsługująca tworzenie nowego produktu
     *
     * @param model the model
     * @return Odnośnik do widoku reprezentującego formularz tworzenia nowego produktu
     */
    @GetMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product/form";
    }

    /**
     * Metoda obsługująca edycję danych produktu
     *
     * @param id    the id
     * @param model the model
     * @return Odnośnik do widoku reprezentującego formularz edycji produktu
     */
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable long id, Model model) {
        Optional<Product> opt = productService.getOne(id);
        if (opt.isEmpty()) return "redirect:/";
        model.addAttribute("product", new ProductDto(opt.get()));
        return "product/form";
    }

    /**
     * Metoda obsługująca zapis danych produktu
     *
     * @param dto   the dto
     * @param model the model
     * @return String Odniesienie do widoku
     */
    @PostMapping("/product/save")
    public String save(@ModelAttribute ProductDto dto, Model model) {
        List<String> errors = productService.validate(dto);
        if (!errors.isEmpty()) {
            model.addAttribute("errorMsg", errors);
            model.addAttribute("product", dto);
            return "product/form";
        }

        long result = productService.save(dto);
        if(result == 0) return "redirect:/";

        return "redirect:/product/details/" + result;
    }

    /**
     * Metoda odpowiedzialna za usuwanie danych produktu
     *
     * @param id         the id
     * @param attributes the attributes
     * @return the redirect view
     */
    @GetMapping("/product/delete/{id}")
    public RedirectView delete(@PathVariable long id, RedirectAttributes attributes) {

        productService.delete(id);
        // TODO sprawdzenie czy można usunąć
        attributes.addAttribute("successAlert", "Usunięto produkt.");

        return new RedirectView("/");
    }
}
