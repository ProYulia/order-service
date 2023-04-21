package ru.yandex.yandexlavka.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    private int courierID;

    //@Enumerated(EnumType.STRING)
    @Column(name = "courier_type")
    private CourierType courierType;

    @OneToMany(mappedBy = "courier", fetch = FetchType.EAGER)
    private List<Region> regions;

    @OneToMany
    private List<Shift> shifts;

    public Courier() {}

    public Courier(CourierType courierType, List<Region> regions, List<Shift> shifts) {
        this.courierType = courierType;
        this.regions = regions;
        this.shifts = shifts;
    }

    public enum CourierType {
        FOOT, BIKE, AUTO
    }

    public int getCourierID() {
        return courierID;
    }

    public void setCourierID(int courierID) {
        this.courierID = courierID;
    }

    public CourierType getCourierType() {
        return courierType;
    }

    public void setCourierType(CourierType courierType) {
        this.courierType = courierType;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
}
