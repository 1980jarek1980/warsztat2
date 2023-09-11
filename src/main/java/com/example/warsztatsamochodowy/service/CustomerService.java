package com.example.warsztatsamochodowy.service;

import com.example.warsztatsamochodowy.entity.LegalPerson;
import com.example.warsztatsamochodowy.entity.PhysicalPerson;
import com.example.warsztatsamochodowy.repository.LegalPersonRepository;
import com.example.warsztatsamochodowy.repository.PhysicalPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final LegalPersonRepository legalPersonRepository;
    private final PhysicalPersonRepository physicalPersonRepository;

    @Autowired
    public CustomerService(LegalPersonRepository legalPersonRepository, PhysicalPersonRepository physicalPersonRepository) {
        this.legalPersonRepository = legalPersonRepository;
        this.physicalPersonRepository = physicalPersonRepository;
    }

    public Iterable<LegalPerson> getLegalPersons(){
        return legalPersonRepository.findByOrderByNazwaAsc();
    }

    public Iterable<PhysicalPerson> getPhysicalPersons(){
        return physicalPersonRepository.findByOrderByNazwiskoAsc();
    }
}
