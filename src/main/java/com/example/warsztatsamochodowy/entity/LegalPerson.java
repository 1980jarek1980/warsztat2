package com.example.warsztatsamochodowy.entity;

import com.example.warsztatsamochodowy.dto.UserDto;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class LegalPerson extends User{

    private String nazwa;
    private String nip;
    private String regon;

    public LegalPerson() {
    }

    public LegalPerson(UserDto userDto, Set<Role> roles) {
        super(userDto, roles);
        this.nazwa = userDto.getNazwa();
        this.nip = userDto.getNip();
        this.regon = userDto.getRegon();
    }

    public LegalPerson(String password, String email, String adres, String telefon, Set<Role> roles, String nazwa, String nip, String regon) {
        super(password, email, adres, telefon, roles);
        this.nazwa = nazwa;
        this.nip = nip;
        this.regon = regon;
    }

    @Override
    public String getMainName() {
        return nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }
}
