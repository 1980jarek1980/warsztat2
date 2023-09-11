package com.example.warsztatsamochodowy.service;

import com.example.warsztatsamochodowy.dto.CarDto;
import com.example.warsztatsamochodowy.entity.Car;
import com.example.warsztatsamochodowy.entity.Order;
import com.example.warsztatsamochodowy.entity.User;
import com.example.warsztatsamochodowy.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository repository;

    @Autowired
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public Iterable<Car> getAll() {
        return repository.findAll();
    }

    public Iterable<Car> getListByCustomer(User user) {
        return repository.findAllByOwner(user);
    }

    public Optional<Car> getOne(long id) {
        return repository.findById(id);
    }

    public List<String> validate(CarDto dto) {
        List<String> errors = new ArrayList<>();

        if (repository.existsByVin(dto.getVin()) && dto.getId() == 0) {
            errors.add("Istnieje samochód o takim numerze VIN.");
            return errors;
        }

        if (dto.getMarka().isBlank()) {
            errors.add("Marka jest wymagana.");
        }
        if (dto.getModel().isBlank()) {
            errors.add("Model jest wymagany.");
        }
        if (dto.getVin().isBlank()) {
            errors.add("VIN jest wymagany.");
        }
        if (dto.getNumerRejestracyjny().isBlank()) {
            errors.add("Numer rejestracyjny jest wymagany.");
        }
        return errors;
    }

    public long save(CarDto dto) {
        Car car;
        if(dto.getId() == 0){
            car = new Car();
        }else{
            Optional<Car> opt = repository.findById(dto.getId());
            if(opt.isEmpty()) return -1;
            car = opt.get();
        }
        car.setMarka(dto.getMarka());
        car.setModel(dto.getModel());
        car.setGeneracja(dto.getGeneracja());
        car.setPrzebieg(dto.getPrzebieg());
        car.setVin(dto.getVin());
        car.setRok(dto.getRok());
        car.setNumerRejestracyjny(dto.getNumerRejestracyjny());
        car.setTypPodwozia(dto.getTypPodwozia());
        car.setKolor(dto.getKolor());
        car.setRodzajPaliwa(dto.getRodzajPaliwa());
        if(dto.getUser() != null){
            car.setOwner(dto.getUser());
        }
        car = repository.save(car);
        return car.getId();
    }

    public boolean delete(long id){
        Optional<Car> opt = getOne(id);
        if(opt.isEmpty()) return false;
        Car car = opt.get();

        if(!car.getOrders().isEmpty()) return false;

        repository.deleteById(id);
        return true;
    }
}
