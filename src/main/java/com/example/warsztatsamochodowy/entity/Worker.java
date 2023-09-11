package com.example.warsztatsamochodowy.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Worker extends User {

    private String imie;
    private String nazwisko;
    private String pesel;
    private String dataurodzenia;
    private String stanowiskopracy;
    @ManyToMany(mappedBy = "pracownicy")
    private List<Order> zamowienia;

    public Worker() {
    }

    public Worker(String password, String email, String adres, String telefon, Set<Role> roles, String imie, String nazwisko, String pesel, String dataurodzenia, String stanowiskopracy, List<Order> zamowienia) {
        super(password, email, adres, telefon, roles);
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.dataurodzenia = dataurodzenia;
        this.stanowiskopracy = stanowiskopracy;
        this.zamowienia = zamowienia;
    }

    @Override
    public String getMainName() {
        return imie + " " + nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getDataurodzenia() {
        return dataurodzenia;
    }

    public void setDataurodzenia(String dataurodzenia) {
        this.dataurodzenia = dataurodzenia;
    }

    public String getStanowiskopracy() {
        return stanowiskopracy;
    }

    public void setStanowiskopracy(String stanowiskopracy) {
        this.stanowiskopracy = stanowiskopracy;
    }

    public List<Order> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(List<Order> zamowienia) {
        this.zamowienia = zamowienia;
    }
}
