package com.example.warsztatsamochodowy.service;

import com.example.warsztatsamochodowy.dto.ServiceDto;
import com.example.warsztatsamochodowy.entity.Service;
import com.example.warsztatsamochodowy.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServicesService {

    private final ServiceRepository repository;

    @Autowired
    public ServicesService(ServiceRepository repository) {
        this.repository = repository;
    }

    public Iterable<Service> getAll() {
        return repository.findAll();
    }

    public Iterable<ServiceDto> getAllDto() {
        return repository.countAllDto();
    }

    public Optional<Service> getOne(long id) {
        return repository.findById(id);
    }

    public List<String> validate(ServiceDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto.getNazwa().isBlank()) {
            errors.add("Nazwa jest wymagana.");
        }
        if (dto.getProduct() == null) {
            errors.add("Produkt jest wymagany");
        }

        return errors;
    }

    public void save(ServiceDto dto) {
        Service service;
        if (dto.getId() == 0) {
            service = new Service();
        } else {
            Optional<Service> opt = repository.findById(dto.getId());
            if (opt.isEmpty()) return;
            service = opt.get();
        }
        service.setNazwa(dto.getNazwa());
        service.setOrder(dto.getOrder());
        service.setProduct(dto.getProduct());
        service.setCount(dto.getCount());
        service.setCena(dto.getCena() + (dto.getProduct().getCena() * dto.getCount()));
        repository.save(service);
    }

    public void delete(Service service) {
        repository.delete(service);
    }
}
