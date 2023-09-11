package com.example.warsztatsamochodowy.controller;

import com.example.warsztatsamochodowy.dto.WorkerDto;
import com.example.warsztatsamochodowy.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

/**
 * Kontroler odpowiedzialny za obsługę pracowników
 */
@Controller
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerService workerService;

    /**
     * Instantiates a new Worker controller.
     *
     * @param workerService the worker service
     */
    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    /**
     * Strona główna wyświetla listę pracowników
     *
     * @param model the model
     * @return Odnośnik do widoku listy pracowników
     */
    @GetMapping("/list")
    public String index(Model model) {
        model.addAttribute("type", "pracowników");
        model.addAttribute("page", "worker_list");
        model.addAttribute("list", workerService.getAll());
        return "user/list";
    }

    /**
     * Metoda obsługująca tworzenie nowego pracownika
     *
     * @param model the model
     * @return Odnośnik do widoku reprezentującego formularz tworzenia nowego pracownika
     */
    @GetMapping("/new")
    public String newWorker(Model model) {
        model.addAttribute("worker", new WorkerDto());
        return "worker/form";
    }

    /**
     * Metoda obsługująca zapis danych praconika
     *
     * @param model the model
     * @param dto   the dto
     * @return the string
     */
    @PostMapping("/save")
    public String save(Model model, @ModelAttribute WorkerDto dto) {

        List<String> errors = workerService.validate(dto);
        if (!errors.isEmpty()) {
            model.addAttribute("errorMsg", errors);
            model.addAttribute("worker", dto);
            return "worker/form";
        }

        long id = workerService.save(dto);
        if (id == 0) return "redirect:/";

        return "redirect:/user/details/" + id;
    }

    /**
     * Metoda odpowiedzialna za usuwanie danych pracownika
     *
     * @param id         the id
     * @param attributes the attributes
     * @return the redirect view
     */
    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable long id, RedirectAttributes attributes) {

        if (workerService.delete(id)) {
            attributes.addAttribute("successAlert", "Usunięto pracownika.");
            return new RedirectView("/worker/list");
        }

        attributes.addAttribute("errorAlert", "Nie można usunąć.");
        return new RedirectView("/worker/details/" + id);
    }
}