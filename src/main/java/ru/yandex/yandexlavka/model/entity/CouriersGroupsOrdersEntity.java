package ru.yandex.yandexlavka.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "courier_group")
@Getter
@Setter
@NoArgsConstructor
public class CouriersGroupsOrdersEntity {

    @Id
    @SequenceGenerator(name="groupSequence", sequenceName="group_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="groupSequence")
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "courier_id")
    private Integer courierId;

    @ElementCollection
    @CollectionTable(name = "order_group", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "order_id")
    private List<Integer> orders;

    public CouriersGroupsOrdersEntity(Integer courierId, List<Integer> orders) {
        this.courierId = courierId;
        this.orders = orders;
    }
}
