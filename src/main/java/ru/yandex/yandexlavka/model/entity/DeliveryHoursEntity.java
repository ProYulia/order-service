package ru.yandex.yandexlavka.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

@Entity
@Table(name = "delivery_hours")
@Getter
@Setter
public class DeliveryHoursEntity {
    @Id
    @SequenceGenerator(name="dhSequence", sequenceName="dh_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="dhSequence")
    @Column(name = "id")
    private int id;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "deliveryHours")
    private OrderEntity order;

    @Override
    public String toString() {
        return startTime.format(DateTimeFormatter.ofPattern("HH:mm")) +
                "-" +
                endTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
