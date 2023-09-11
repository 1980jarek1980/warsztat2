package com.example.warsztatsamochodowy.repository;

import com.example.warsztatsamochodowy.entity.LegalPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LegalPersonRepository extends JpaRepository<LegalPerson,Long> {
    List<LegalPerson> findByOrderByNazwaAsc();
}
