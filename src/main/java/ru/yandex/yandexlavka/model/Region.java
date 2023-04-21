package ru.yandex.yandexlavka.model;

import jakarta.persistence.*;

@Entity
@Table
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private int id;

    @Column(name = "region_number")
    private int regionNumber;

    @ManyToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "courier_id")
    private Courier courier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(int regionNumber) {
        this.regionNumber = regionNumber;
    }

}
