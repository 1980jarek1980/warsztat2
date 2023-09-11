package com.example.warsztatsamochodowy.dto;

import com.example.warsztatsamochodowy.entity.Car;
import com.example.warsztatsamochodowy.entity.User;

/**
 * The type Car dto.
 * DTO, czyli Data Transfer Object, to wzorzec projektowy (design pattern) używany w programowaniu do przesyłania danych między różnymi warstwami aplikacji lub między aplikacjami.
 * Jest to obiekt, który przenosi dane, ale nie zawiera w sobie żadnej logiki.
 * Wyizolowanie logiki od procesu przesyłania danych. Pozwala to na lepszą kontrolę nad danymi przesyłanymi między różnymi częściami systemu.
 */
public class CarDto {

    private long id = 0;
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
    private User user;

    /**
     * Instantiates a new Car dto.
     */
    public CarDto() {
    }

    /**
     * Instantiates a new Car dto.
     *
     * @param car the car
     */
    public CarDto(Car car) {
        this.id = car.getId();
        this.marka = car.getMarka();
        this.model = car.getModel();
        this.generacja = car.getGeneracja();
        this.przebieg = car.getPrzebieg();
        this.vin = car.getVin();
        this.rok = car.getRok();
        this.numerRejestracyjny = car.getNumerRejestracyjny();
        this.typPodwozia = car.getTypPodwozia();
        this.kolor = car.getKolor();
        this.rodzajPaliwa = car.getRodzajPaliwa();
        this.user = car.getOwner();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets marka.
     *
     * @return the marka
     */
    public String getMarka() {
        return marka;
    }

    /**
     * Sets marka.
     *
     * @param marka the marka
     */
    public void setMarka(String marka) {
        this.marka = marka;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets model.
     *
     * @param model the model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets generacja.
     *
     * @return the generacja
     */
    public int getGeneracja() {
        return generacja;
    }

    /**
     * Sets generacja.
     *
     * @param generacja the generacja
     */
    public void setGeneracja(int generacja) {
        this.generacja = generacja;
    }

    /**
     * Gets przebieg.
     *
     * @return the przebieg
     */
    public int getPrzebieg() {
        return przebieg;
    }

    /**
     * Sets przebieg.
     *
     * @param przebieg the przebieg
     */
    public void setPrzebieg(int przebieg) {
        this.przebieg = przebieg;
    }

    /**
     * Gets vin.
     *
     * @return the vin
     */
    public String getVin() {
        return vin;
    }

    /**
     * Sets vin.
     *
     * @param vin the vin
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Gets rok.
     *
     * @return the rok
     */
    public int getRok() {
        return rok;
    }

    /**
     * Sets rok.
     *
     * @param rok the rok
     */
    public void setRok(int rok) {
        this.rok = rok;
    }

    /**
     * Gets numer rejestracyjny.
     *
     * @return the numer rejestracyjny
     */
    public String getNumerRejestracyjny() {
        return numerRejestracyjny;
    }

    /**
     * Sets numer rejestracyjny.
     *
     * @param numerRejestracyjny the numer rejestracyjny
     */
    public void setNumerRejestracyjny(String numerRejestracyjny) {
        this.numerRejestracyjny = numerRejestracyjny;
    }

    /**
     * Gets typ podwozia.
     *
     * @return the typ podwozia
     */
    public String getTypPodwozia() {
        return typPodwozia;
    }

    /**
     * Sets typ podwozia.
     *
     * @param typPodwozia the typ podwozia
     */
    public void setTypPodwozia(String typPodwozia) {
        this.typPodwozia = typPodwozia;
    }

    /**
     * Gets kolor.
     *
     * @return the kolor
     */
    public String getKolor() {
        return kolor;
    }

    /**
     * Sets kolor.
     *
     * @param kolor the kolor
     */
    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    /**
     * Gets rodzaj paliwa.
     *
     * @return the rodzaj paliwa
     */
    public String getRodzajPaliwa() {
        return rodzajPaliwa;
    }

    /**
     * Sets rodzaj paliwa.
     *
     * @param rodzajPaliwa the rodzaj paliwa
     */
    public void setRodzajPaliwa(String rodzajPaliwa) {
        this.rodzajPaliwa = rodzajPaliwa;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
