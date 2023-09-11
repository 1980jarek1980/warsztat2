package com.example.warsztatsamochodowy.repository;

import com.example.warsztatsamochodowy.entity.Order;
import com.example.warsztatsamochodowy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCarOwner(User user);

    List<Order> findAllByPracownicyContaining(User user);
}
