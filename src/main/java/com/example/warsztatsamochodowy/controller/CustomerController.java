package com.example.warsztatsamochodowy.controller;

import com.example.warsztatsamochodowy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Kontroler odpowiedzialny za obsługę informacji o Klientach
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    /**
     * Instantiates a new Customer controller.
     *
     * @param customerService the customer service
     */
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Metoda przekazuje do widoku user/list dane Osób Prawnych
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/legal")
    public String legalCustomers(Model model){
        model.addAttribute("type", "osób prawnych");
        model.addAttribute("page", "legal_customer_list");
        model.addAttribute("list", customerService.getLegalPersons());
        return "user/list";
    }

    /**
     * Metoda przekazuje do widoku user/list dane Osób Fizycznych
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/physical")
    public String physicalCustomers(Model model){
        model.addAttribute("type", "osób fizycznych");
        model.addAttribute("page", "physical_customer_list");
        model.addAttribute("list", customerService.getPhysicalPersons());
        return "user/list";
    }
}
