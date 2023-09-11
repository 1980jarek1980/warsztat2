package com.example.warsztatsamochodowy.dto;

import com.example.warsztatsamochodowy.entity.Car;
import com.example.warsztatsamochodowy.entity.Order;
import com.example.warsztatsamochodowy.entity.Service;
import com.example.warsztatsamochodowy.entity.Worker;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto {

    private long id = 0;
    private String sygnaturaZamowienia;
    private int liczbaRoboczogodzin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataRozpoczecia;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataZakonczenia;
    private OrderStatus status;
    private String faktura;
    private MultipartFile invoice;
    private Car car;
    private List<Worker> pracownicy;
    private List<Service> services;

    public OrderDto() {
        pracownicy = new ArrayList<>();
        services = new ArrayList<>();
        dataRozpoczecia = new Date();
        dataZakonczenia = new Date();
    }

    public OrderDto(Order order) {
        this.id = order.getId();
        this.sygnaturaZamowienia = order.getSygnaturaZamowienia();
        this.liczbaRoboczogodzin = order.getLiczbaRoboczogodzin();
        this.dataRozpoczecia = order.getDataRozpoczecia();
        this.dataZakonczenia = order.getDataZakonczenia();
        this.status = order.getStatus();
        this.faktura = order.getFaktura();
        this.car = order.getCar();
        this.pracownicy = order.getPracownicy();
        this.services = order.getServices();
    }

    public OrderDto(long id, String sygnaturaZamowienia, int liczbaRoboczogodzin, Date dataRozpoczecia, Date dataZakonczenia, OrderStatus status, String faktura, Car car, List<Worker> pracownicy, List<Service> services) {
        this.id = id;
        this.sygnaturaZamowienia = sygnaturaZamowienia;
        this.liczbaRoboczogodzin = liczbaRoboczogodzin;
        this.dataRozpoczecia = dataRozpoczecia;
        this.dataZakonczenia = dataZakonczenia;
        this.status = status;
        this.faktura = faktura;
        this.car = car;
        this.pracownicy = pracownicy;
        this.services = services;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSygnaturaZamowienia() {
        return sygnaturaZamowienia;
    }

    public void setSygnaturaZamowienia(String sygnaturaZamowienia) {
        this.sygnaturaZamowienia = sygnaturaZamowienia;
    }

    public int getLiczbaRoboczogodzin() {
        return liczbaRoboczogodzin;
    }

    public void setLiczbaRoboczogodzin(int liczbaRoboczogodzin) {
        this.liczbaRoboczogodzin = liczbaRoboczogodzin;
    }

    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public Date getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Date dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getFaktura() {
        return faktura;
    }

    public void setFaktura(String faktura) {
        this.faktura = faktura;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Worker> getPracownicy() {
        return pracownicy;
    }

    public void setPracownicy(List<Worker> pracownicy) {
        this.pracownicy = pracownicy;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public MultipartFile getInvoice() {
        return invoice;
    }

    public void setInvoice(MultipartFile invoice) {
        this.invoice = invoice;
    }
}
