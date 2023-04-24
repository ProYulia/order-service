package ru.yandex.yandexlavka.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "shift")
@Getter
@Setter
public class ShiftEntity {
    @Id
    @SequenceGenerator(name="shiftSequence", sequenceName="shift_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="shiftSequence")
    @Column(name = "shift_id")
    private int id;

    @Column(name = "time_start")
    private LocalTime startTime;

    @Column(name = "time_end")
    private LocalTime endTime;

}
