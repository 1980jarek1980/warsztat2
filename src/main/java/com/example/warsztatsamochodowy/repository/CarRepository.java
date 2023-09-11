package com.example.warsztatsamochodowy.repository;

import com.example.warsztatsamochodowy.entity.Car;
import com.example.warsztatsamochodowy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findById (long id);

    List<Car> findAllByOwner(User user);

    boolean existsByVin(String vin);
}
