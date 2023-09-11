package com.example.warsztatsamochodowy.dto;

import com.example.warsztatsamochodowy.entity.Worker;

public class WorkerDto {

    private long id = 0;
    private String imie;
    private String nazwisko;
    private String password;
    private String confirmPassword;
    private String email;
    private String adres;
    private String telefon;
    private String pesel;
    private String dataurodzenia;
    private String stanowiskopracy;

    public WorkerDto() {
    }

    public WorkerDto(Worker worker) {
        this.id = worker.getId();
        this.imie = worker.getImie();
        this.nazwisko = worker.getNazwisko();
        this.password = worker.getPassword();
        this.confirmPassword = "";
        this.email = worker.getEmail();
        this.adres = worker.getAdres();
        this.telefon = worker.getTelefon();
        this.pesel = worker.getPesel();
        this.dataurodzenia = worker.getDataurodzenia();
        this.stanowiskopracy = worker.getStanowiskopracy();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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
}
