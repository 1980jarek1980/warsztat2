package com.example.warsztatsamochodowy.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double cena;
    private String nazwa;
    private String rodzaj;
    private String opis;
    @OneToMany(mappedBy = "product")
    private List<Service> uslugi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public List<Service> getUslugi() {
        return uslugi;
    }

    public void setUslugi(List<Service> uslugi) {
        this.uslugi = uslugi;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
