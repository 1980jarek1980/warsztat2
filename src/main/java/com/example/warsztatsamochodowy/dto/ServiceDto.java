package com.example.warsztatsamochodowy.dto;

import com.example.warsztatsamochodowy.entity.Order;
import com.example.warsztatsamochodowy.entity.Product;
import com.example.warsztatsamochodowy.entity.Service;

public class ServiceDto {
    private long id = 0;
    private String nazwa;
    private double cena;
    private int count = 1;
    private Product product;
    private Order order;

    public ServiceDto() {
    }

    public ServiceDto(Order order) {
        this.order = order;
    }

    public ServiceDto(Service service) {
        this.order = service.getOrder();
        this.id = service.getId();
        this.nazwa = service.getNazwa();
        this.cena = service.getCena();
        this.product = service.getProduct();
        this.count = service.getCount();
    }

    public ServiceDto(long id, String nazwa, double cena) {
        this.id = id;
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
