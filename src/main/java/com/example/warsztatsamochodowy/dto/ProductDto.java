package com.example.warsztatsamochodowy.dto;

import com.example.warsztatsamochodowy.entity.Product;

public class ProductDto {
    private long id = 0;
    private double cena;
    private String nazwa;
    private String rodzaj;
    private String opis;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.cena = product.getCena();
        this.nazwa = product.getNazwa();
        this.rodzaj = product.getRodzaj();
        this.opis = product.getOpis();
    }

    public ProductDto(double cena, String nazwa, String rodzaj, String opis) {
        this.cena = cena;
        this.nazwa = nazwa;
        this.rodzaj = rodzaj;
        this.opis = opis;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
