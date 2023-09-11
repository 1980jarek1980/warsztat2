package com.example.warsztatsamochodowy.repository;

import com.example.warsztatsamochodowy.dto.ServiceDto;
import com.example.warsztatsamochodowy.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    @Query("select new com.example.warsztatsamochodowy.dto.ServiceDto(s.id, s.nazwa, s.cena)" +
            " from Service s order by s.nazwa asc")
    List<ServiceDto> countAllDto();
}
