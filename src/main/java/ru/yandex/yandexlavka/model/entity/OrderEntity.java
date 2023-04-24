package ru.yandex.yandexlavka.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToMany
    private List<ShiftEntity> deliveryHours;

    @Column(name = "order_cost")
    private int cost;

    @Column(name = "complete_time")
    private String completeTime; //todo change to localTime

    public OrderEntity() {
    }

    public OrderEntity(float weight,
                       RegionEntity regionEntity,
                       List<ShiftEntity> deliveryHours,
                       int cost) {
        this.weight = weight;
        this.regionEntity = regionEntity;
        this.deliveryHours = deliveryHours;
        this.cost = cost;
    }

}
