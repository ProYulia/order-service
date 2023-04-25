package ru.yandex.yandexlavka.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "working_hours")
@Getter
@Setter
public class WorkingHoursEntity {
    @Id
    @SequenceGenerator(name="whSequence", sequenceName="wh_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="whSequence")
    @Column(name = "id")
    private int id;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "workingHours")
    private CourierEntity courier;

    @Override
    public String toString() {
        return startTime.format(DateTimeFormatter.ofPattern("HH:mm")) +
                "-" +
                endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

}
