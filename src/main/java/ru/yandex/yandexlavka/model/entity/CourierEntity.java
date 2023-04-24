package ru.yandex.yandexlavka.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "courierEntity")
    private List<RegionEntity> regionEntities;

    @OneToMany
    private List<ShiftEntity> shiftEntities;

    public CourierEntity() {}

    public List<String> shiftEntitiesToList() {
        List<String> result = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for(ShiftEntity entity : shiftEntities) {
            String shift = entity.getStartTime().format(formatter) +
                    "-" +
                    entity.getStartTime().format(formatter);

            result.add(shift);
        }
        return result;
    }



    public CourierEntity(CourierType courierType,
                         List<RegionEntity> regionEntities,
                         List<ShiftEntity> shiftEntities) {
        this.courierType = courierType;
        this.regionEntities = regionEntities;
        this.shiftEntities = shiftEntities;
    }

    public enum CourierType {
        FOOT, BIKE, AUTO
    }

}
