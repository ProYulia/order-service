package ru.yandex.yandexlavka.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "courier")
@Getter
@Setter
public class CourierEntity {

    @Id
    @SequenceGenerator(name="courierSequence", sequenceName="courier_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="courierSequence")
    @Column(name = "courier_id")
    private int courierID;

    //@Enumerated(EnumType.STRING)
    @Column(name = "courier_type")
    private CourierType courierType;


    @ManyToMany
    @JoinTable(name = "courier_region",
            joinColumns = @JoinColumn(name = "courier_id"),
            inverseJoinColumns = @JoinColumn(name = "region_id")
    )
    private List<RegionEntity> regionEntities;


    @OneToMany(mappedBy = "courierId")
    private List<WorkingHoursEntity> workingHours;

    public CourierEntity() {}

    public CourierEntity(CourierType courierType,
                         List<RegionEntity> regionEntities,
                         List<WorkingHoursEntity> workingHours) {
        this.courierType = courierType;
        this.regionEntities = regionEntities;
        this.workingHours = workingHours;
    }

    public enum CourierType {
        FOOT("FOOT"),

        BIKE("BIKE"),

        AUTO("AUTO");

        private String value;

        CourierType(String value) {this.value = value;}
    }

}
