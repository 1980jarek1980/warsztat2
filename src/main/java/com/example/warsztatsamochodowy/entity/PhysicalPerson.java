package com.example.warsztatsamochodowy.entity;

import com.example.warsztatsamochodowy.dto.UserDto;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class PhysicalPerson extends User {

    private String imie;
    private String nazwisko;
    private String pesel;
    private String nip;

    public PhysicalPerson() {
    }

    public PhysicalPerson(UserDto userDto, Set<Role> roles) {
        super(userDto, roles);
        this.imie = userDto.getImie();
        this.nazwisko = userDto.getNazwisko();
        this.pesel = userDto.getPesel();
        this.nip = userDto.getNip();
    }

    public PhysicalPerson(String password, String email, String adres, String telefon, Set<Role> roles, String imie, String nazwisko, String pesel, String nip) {
        super(password, email, adres, telefon, roles);
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.nip = nip;
    }

    @Override
    public String getMainName() {
        return imie +" "+nazwisko;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
