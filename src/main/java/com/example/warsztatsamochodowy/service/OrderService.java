package com.example.warsztatsamochodowy.service;

import com.example.warsztatsamochodowy.dto.OrderDto;
import com.example.warsztatsamochodowy.dto.OrderStatus;
import com.example.warsztatsamochodowy.entity.*;
import com.example.warsztatsamochodowy.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class OrderService {

    private final OrderRepository repository;
    private final StorageService storageService;

    @Autowired
    public OrderService(OrderRepository repository, StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }

    public Iterable<Order> getList() {
        return repository.findAll();
    }

    public Iterable<Order> getListByWorker(Worker worker) {
        return repository.findAllByPracownicyContaining(worker);
    }

    public Iterable<Order> getListByCustomer(User user) {
        return repository.findAllByCarOwner(user);
    }

    public Optional<Order> getOne(long id) {
        return repository.findById(id);
    }

    public List<String> validate(OrderDto dto) {
        List<String> errors = new ArrayList<>();

//        if (dto.getNazwa().isBlank()) {
//            errors.add("Nazwa jest wymagana.");
//        }

        if (dto.getSygnaturaZamowienia().isBlank()) {
            errors.add("Sygnatura jest wymagana.");
        }
        if (dto.getLiczbaRoboczogodzin() <= 0) {
            errors.add("Liczba godzin musi być dodatnia jest wymagana.");
        }
        if (dto.getCar() == null) {
            errors.add("Samochód jest wymagany.");
        }

        return errors;
    }

    public long save(OrderDto dto) {
        Order order;
        if (dto.getId() == 0) {
            order = new Order();
        } else {
            Optional<Order> opt = repository.findById(dto.getId());
            if (opt.isEmpty()) return 0;
            order = opt.get();
        }

        order.setId(dto.getId());
        order.setSygnaturaZamowienia(dto.getSygnaturaZamowienia());
        order.setLiczbaRoboczogodzin(dto.getLiczbaRoboczogodzin());
        order.setDataRozpoczecia(dto.getDataRozpoczecia());
        order.setDataZakonczenia(dto.getDataZakonczenia());
        order.setStatus(dto.getStatus());
        order.setCar(dto.getCar());

        if (dto.getPracownicy() != null) {
            order.setPracownicy(dto.getPracownicy());
        }

        order = repository.save(order);

        if (dto.getInvoice() != null && !dto.getInvoice().isEmpty()) {
            storageService.save(order.getId(), dto.getInvoice());
            order.setFaktura(dto.getInvoice().getOriginalFilename());
            order = repository.save(order);
        }

        return order.getId();
    }
}
