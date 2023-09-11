package com.example.warsztatsamochodowy.repository;

import com.example.warsztatsamochodowy.entity.PhysicalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhysicalPersonRepository extends JpaRepository<PhysicalPerson,Long> {
    List<PhysicalPerson> findByOrderByNazwiskoAsc();
}
