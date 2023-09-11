package com.example.warsztatsamochodowy.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Car {
    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private Long id;
    private String marka;
    private String model;
    private int generacja;
    private int przebieg;
    private String vin;
    private int rok;
    private String numerRejestracyjny;
    private String typPodwozia;
    private String kolor;
    private String rodzajPaliwa;
    @OneToMany(mappedBy = "car")
    private Set<Order> orders;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getGeneracja() {
        return generacja;
    }

    public void setGeneracja(int generacja) {
        this.generacja = generacja;
    }

    public int getPrzebieg() {
        return przebieg;
    }

    public void setPrzebieg(int przebieg) {
        this.przebieg = przebieg;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    public String getTypPodwozia() {
        return typPodwozia;
    }

    public void setTypPodwozia(String typPodwozia) {
        this.typPodwozia = typPodwozia;
    }

    public String getKolor() {
        return kolor;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public String getRodzajPaliwa() {
        return rodzajPaliwa;
    }

    public void setRodzajPaliwa(String rodzajPaliwa) {
        this.rodzajPaliwa = rodzajPaliwa;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
