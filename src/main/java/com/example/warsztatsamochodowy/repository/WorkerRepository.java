package com.example.warsztatsamochodowy.repository;

import com.example.warsztatsamochodowy.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    boolean existsByEmail(String email);
}
