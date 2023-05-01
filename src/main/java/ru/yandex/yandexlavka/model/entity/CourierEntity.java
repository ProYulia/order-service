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
    @SequenceGenerator(name = "courierSequence", sequenceName = "courier_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courierSequence")
    @Column(name = "courier_id")
    private int courierID;

    //@Enumerated(EnumType.STRING)
    @Column(name = "courier_type")
    private CourierType courierType;


    @ElementCollection
    @CollectionTable(name = "regions", joinColumns = @JoinColumn(name = "courier_id"))
    @Column(name = "regions")
    private List<Integer> regions;


    @ElementCollection
    @CollectionTable(name = "working_hours", joinColumns = @JoinColumn(name = "courier_id"))
    @Column(name = "working_hours")
    private List<String> workingHours;


    public CourierEntity() {
    }

    public CourierEntity(CourierType courierType,
                         List<Integer> regions,
                         List<String> workingHours) {
        this.courierType = courierType;
        this.regions = regions;
        this.workingHours = workingHours;
    }

    public enum CourierType {
        FOOT("FOOT"),

        BIKE("BIKE"),

        AUTO("AUTO");

        private String value;

        CourierType(String value) {
            this.value = value;
        }
    }

}
