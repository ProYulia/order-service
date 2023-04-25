package ru.yandex.yandexlavka.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity {

    @Id
    @SequenceGenerator(name="ordersSequence", sequenceName="orders_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ordersSequence")
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_weight")
    private float weight;

    @OneToOne
    private RegionEntity regionEntity;

    @OneToMany(mappedBy = "order")
    private List<DeliveryHoursEntity> deliveryHours;

    @Column(name = "order_cost")
    private int cost;

    @Column(name = "complete_time")
    private LocalTime completeTime;

    public OrderEntity() {
    }

    public OrderEntity(float weight,
                       RegionEntity regionEntity,
                       List<DeliveryHoursEntity> deliveryHours,
                       int cost) {
        this.weight = weight;
        this.regionEntity = regionEntity;
        this.deliveryHours = deliveryHours;
        this.cost = cost;
    }

}
