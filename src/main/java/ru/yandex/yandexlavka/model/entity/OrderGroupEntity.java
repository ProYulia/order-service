package ru.yandex.yandexlavka.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "order_group")
@Getter
@Setter
@NoArgsConstructor
public class OrderGroupEntity {

    public OrderGroupEntity(CourierEntity courier, List<OrderEntity> orders) {
        this.courier = courier;
        this.orders = orders;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer groupId;

    @OneToOne
    private CourierEntity courier;

    @OneToMany(fetch = FetchType.EAGER)
    private List<OrderEntity> orders;
}
