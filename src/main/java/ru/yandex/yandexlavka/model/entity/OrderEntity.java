package ru.yandex.yandexlavka.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {

    @Id
    @SequenceGenerator(name="ordersSequence", sequenceName="orders_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ordersSequence")
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "region")
    private Integer region;

    @ElementCollection
    @CollectionTable(name = "delivery_hours", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "delivery_hours")
    private List<String> deliveryHours;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "complete_time")
    private Instant completeTime;

    @Column(name = "status")
    private String orderStatus;


}
