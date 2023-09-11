package com.example.warsztatsamochodowy.dto;

import com.example.warsztatsamochodowy.entity.LegalPerson;
import com.example.warsztatsamochodowy.entity.PhysicalPerson;
import com.example.warsztatsamochodowy.entity.User;

public class UserDto {

    public static final int CUSTOMER_PERSON = 1;
    public static final int CUSTOMER_ORG = 2;

    private int userType = CUSTOMER_PERSON;
    private long id = 0;
    private String email;
    private String password;
    private String confirmPassword;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String dataurodzenia;
    private String stanowiskopracy;
    private String adres;
    private String telefon;
    private String nip;
    private String nazwa;
    private String regon;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.adres = user.getAdres();
        this.telefon = user.getTelefon();

        if (user instanceof PhysicalPerson p) {
            this.imie = p.getImie();
            this.nazwisko = p.getNazwisko();
            this.nip = p.getNip();
            this.pesel = p.getPesel();
        }
        if (user instanceof LegalPerson l) {
            this.userType = CUSTOMER_ORG;
            this.nazwa = l.getNazwa();
            this.regon = l.getRegon();
            this.nip = l.getNip();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }
}
