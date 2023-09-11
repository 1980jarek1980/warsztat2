package com.example.warsztatsamochodowy.controller;

import com.example.warsztatsamochodowy.dto.CarDto;
import com.example.warsztatsamochodowy.entity.*;
import com.example.warsztatsamochodowy.repository.LegalPersonRepository;
import com.example.warsztatsamochodowy.repository.PhysicalPersonRepository;
import com.example.warsztatsamochodowy.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Kontroler  odpowiedzialny za obsługę informacji o Samochodach
 */
@Controller
@RequestMapping("/car")
public class CarController {

    private final CarService carService;
    private final LegalPersonRepository legalPersonRepository;
    private final PhysicalPersonRepository physicalPersonRepository;


    /**
     * Car controller.
     * Do kontroler wstrzyknięte są zależności CarService, LegalPersonRepository, PhysicalPersonRepository
     * Wstrzyknięcie tych zależności ma na celu umożliwić dostęp do wstrzykniętych usług i repozytoriów
     *
     * @param carService               the car service
     * @param legalPersonRepository    the legal person repository
     * @param physicalPersonRepository the physical person repository
     */
    @Autowired
    public CarController(
            CarService carService,
            LegalPersonRepository legalPersonRepository,
            PhysicalPersonRepository physicalPersonRepository
    ) {
        this.carService = carService;
        this.legalPersonRepository = legalPersonRepository;
        this.physicalPersonRepository = physicalPersonRepository;
    }

    /**
     * Metoda która zwraca listę samochodów
     *
     * Metoda pobiera parametry Model i Authentication i zwraca string(odnośnik do widoku z listą samochodów).
     *
     * @param model
     * @param authentication Obsługa autoryzacji użytkowników
     * @return Odniesienie do widoku car/list
     */
    @GetMapping("/list")
    public String list(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Iterable<Car> list;
        if (user.hasRole(Role.ROLE_ADMIN) || user.hasRole(Role.ROLE_WORKER)) {
            list = carService.getAll();
        } else {
            list = carService.getListByCustomer(user);
        }
        model.addAttribute("cars", list);
        return "car/list";
    }

    /**
     * Obsługa okna szczegółów samochodu
     *
     * @param id             the id
     * @param model          the model
     * @param authentication the authentication
     * @return Odniesienie do widoku car/details
     */
    @GetMapping("/details/{id}")
    public String details(@PathVariable long id, Model model, Authentication authentication) {
        Optional<Car> opt = carService.getOne(id);
        if (opt.isEmpty()) return "redirect:/";

        Car car = opt.get();
        User user = (User) authentication.getPrincipal();
        if (user.hasRole("ROLE_USER") && !car.getOwner().getId().equals(user.getId())) {
            return "redirect:/";
        }

        List<Service> services = car.getOrders().stream()
                .flatMap(order -> order.getServices().stream())
                .toList();

        List<Product> products = services.stream()
                .map(Service::getProduct)
                .filter(Objects::nonNull)
                .toList();

        model.addAttribute("car", car);
        model.addAttribute("services", services);
        model.addAttribute("products", products);
        return "car/details";
    }

    /**
     * Metoda obsługująca tworzenie nowego samochodu
     *
     * @param model the model
     * @return String Odniesienie do widoku car/form
     */
    @GetMapping("/new")
    public String newCar(Model model) {
        model.addAttribute("car", new CarDto());
        loadCustomers(model);
        return "car/form";
    }

    /**
     * Metoda obsługująca edycję danych samochodu
     *
     * @param model the model
     * @param id    the id
     * @return String Odniesienie do widoku car/form
     */
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Optional<Car> opt = carService.getOne(id);
        if (opt.isEmpty()) return "redirect:/";
        model.addAttribute("car", new CarDto(opt.get()));
        loadCustomers(model);
        return "car/form";
    }

    /**
     * Metoda obsługująca zapis danych samochodu
     *
     * @param model          the model
     * @param dto            the dto
     * @param authentication the authentication
     * @return String Odniesienie do widoku
     */
    @PostMapping("/save")
    public String save(Model model, @ModelAttribute CarDto dto, Authentication authentication) {
        List<String> errors = carService.validate(dto);
        if (!errors.isEmpty()) {
            model.addAttribute("errorMsg", errors);
            model.addAttribute("car", dto);
            loadCustomers(model);
            return "car/form";
        }

        if (dto.getUser() == null) {
            User user = (User) authentication.getPrincipal();
            if (user.hasRole(Role.ROLE_USER)) {
                dto.setUser(user);
            }
        }

        long id = carService.save(dto);
        return "redirect:/car/details/" + id;
    }

    /**
     * Metoda odpowiedzialna za usuwanie danych samochodu
     *
     * @param id         the id
     * @param attributes the attributes
     * @return the redirect view
     */
    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable long id, RedirectAttributes attributes) {
        if (carService.delete(id)) {
            attributes.addAttribute("successAlert", "Usunięto samochód.");
            return new RedirectView("/car/list");
        }
        attributes.addAttribute("arrorAlert", "Nie można usunąć.");

        return new RedirectView("/car/edit/" + id);
    }

    /**
     * Metoda przekazuje do widoku car/form dane Osób Fizycznych i Prawnych
     *
     * @param model          the model
     */
    private void loadCustomers(Model model) {
        model.addAttribute("persons", physicalPersonRepository.findByOrderByNazwiskoAsc());
        model.addAttribute("orgs", legalPersonRepository.findByOrderByNazwaAsc());
    }
}
