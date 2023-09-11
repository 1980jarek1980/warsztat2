package com.example.warsztatsamochodowy.entity;

import com.example.warsztatsamochodowy.dto.OrderStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "order_entity")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sygnaturaZamowienia;
    private int liczbaRoboczogodzin;
    @Temporal(TemporalType.DATE)
    private Date dataRozpoczecia;
    @Temporal(TemporalType.DATE)
    private Date dataZakonczenia;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.ACCEPTED;
    private String faktura;
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
    @ManyToMany
    @JoinTable(name = "order_worker",
            joinColumns = @JoinColumn(name = "worker_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private List<Worker> pracownicy;
    @OneToMany(mappedBy = "order")
    private List<Service> services;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        double sum = 0;
        for (Service s : getServices()) {
            sum += s.getCena();
        }
        return sum;
    }

    public String getSygnaturaZamowienia() {
        return sygnaturaZamowienia;
    }

    public void setSygnaturaZamowienia(String sygnaturaZamowienia) {
        this.sygnaturaZamowienia = sygnaturaZamowienia;
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

    public int getLiczbaRoboczogodzin() {
        return liczbaRoboczogodzin;
    }

    public void setLiczbaRoboczogodzin(int liczbaRoboczogodzin) {
        this.liczbaRoboczogodzin = liczbaRoboczogodzin;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
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
}
