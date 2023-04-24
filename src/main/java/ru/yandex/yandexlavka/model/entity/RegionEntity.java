package ru.yandex.yandexlavka.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "region")
@Getter
@Setter
public class RegionEntity {
    @Id
    @SequenceGenerator(name="regionSequence", sequenceName="region_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="regionSequence")
    @Column(name = "region_id")
    private int id;

    @Column(name = "region_number")
    private int regionNumber;

    @ManyToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "courier_id")
    private CourierEntity courierEntity;

}
