package com.example.warsztatsamochodowy.controller;

import com.example.warsztatsamochodowy.dto.UserDto;
import com.example.warsztatsamochodowy.dto.WorkerDto;
import com.example.warsztatsamochodowy.entity.LegalPerson;
import com.example.warsztatsamochodowy.entity.PhysicalPerson;
import com.example.warsztatsamochodowy.entity.User;
import com.example.warsztatsamochodowy.entity.Worker;
import com.example.warsztatsamochodowy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

/**
 * Kontroler odpowiedzialny za obsługę użytkowników
 */
@Controller
public class UserController {

    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Metoda odpowiedzialna za logowanie użytkownika
     *
     * @return Odnośnik do widoku reprezentującego formularz logowania
     */
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    /**
     * Metoda odpowiedzialna za rejestrację użytkownika
     *
     * @param model the model
     * @return Odnośnik do widoku reprezentującego formularz rejestracji
     */
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/register";
    }

    /**
     * Metoda odpowiedzialna za panel aktualnie zalogowanego użytkownika
     *
     * @param model          the model
     * @param authentication the authentication
     * @return Odnośnik do widoku reprezentującego panel użytkownika
     */
    @GetMapping("/user/panel")
    public String panel(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Optional<User> opt= userService.findById(user.getId());
        if(opt.isEmpty()) return "redirect:/";
        setModel(model, opt.get());
        return "user/details";
    }

    /**
     * Metoda odpowiedzialna za okno z danymi wybranego użytkownika
     *
     * @param model the model
     * @param id    the id
     * @return Odnośnik do widoku reprezentującego dane wybranego użytkownika
     */
    @Secured({"ROLE_ADMIN", "ROLE_WORKER"})
    @GetMapping("/user/details/{id}")
    public String details(Model model, @PathVariable long id) {
        Optional<User> opt = userService.findById(id);
        if (opt.isEmpty()) return "redirect:/";
        setModel(model, opt.get());
        return "user/details";
    }

    /**
     * Metoda odpowiedzialna za edycję danych wybranego użytkownika
     *
     * @param model the model
     * @param id    the id
     * @return Odnośnik do widoku reprezentującego edycję danech wybranego użytkownika
     */
    @GetMapping("/user/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Optional<User> opt = userService.findById(id);
        if (opt.isEmpty()) return "redirect:/";
        User user = opt.get();

        if (user instanceof Worker w) {
            model.addAttribute("worker", new WorkerDto(w));
            return "worker/form";
        }

        if(user instanceof LegalPerson || user instanceof PhysicalPerson){
            model.addAttribute("user", new UserDto(user));
            return "user/register";
        }

        model.addAttribute("user", new UserDto(user));
        return "user/user_form";
    }

    /**
     * Metoda odpowiedzialna za rejestrację nowego użytkownika
     *
     * @param userDto the user dto
     * @param model   the model
     * @return Odnośnik do widoku reprezentującego rejestrację użytkownika
     */
    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserDto userDto, Model model) {
        List<String> errors = userService.validate(userDto);
        if (!errors.isEmpty()) {
            model.addAttribute("errorMsg", errors);
            model.addAttribute("user", userDto);
            return "user/register";
        }

        errors = userService.registerUser(userDto);
        if (!errors.isEmpty()) {
            model.addAttribute("errorMsg", errors);
            model.addAttribute("user", userDto);
            return "user/register";
        }

        return "redirect:/";
    }

    /**
     * Form string.
     *
     * @param userDto the user dto
     * @param model   the model
     * @return the string
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/user/save")
    public String save(@ModelAttribute("user") UserDto userDto, Model model) {
        List<String> errors = userService.validateAdmin(userDto);
        if (!errors.isEmpty()) {
            model.addAttribute("errorMsg", errors);
            model.addAttribute("user", userDto);
            return "user/user_form";
        }

        errors = userService.update(userDto);
        if (!errors.isEmpty()) {
            model.addAttribute("errorMsg", errors);
            model.addAttribute("user", userDto);
            return "user/user_form";
        }

        return "redirect:/user/panel";
    }

    private void setModel(Model model, User user){
        PhysicalPerson physicalPerson = null;
        LegalPerson legalPerson = null;
        Worker worker = null;

        if (user instanceof PhysicalPerson) {
            physicalPerson = (PhysicalPerson) user;
        }
        if (user instanceof LegalPerson) {
            legalPerson = (LegalPerson) user;
        }
        if (user instanceof Worker) {
            worker = (Worker) user;
        }

        model.addAttribute("user", user);
        model.addAttribute("worker", worker);
        model.addAttribute("person", physicalPerson);
        model.addAttribute("org", legalPerson);
    }
}
