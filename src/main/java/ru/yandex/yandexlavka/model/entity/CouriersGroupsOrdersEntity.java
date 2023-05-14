package ru.yandex.yandexlavka.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @Column(name = "date")
    private String date;

    public CouriersGroupsOrdersEntity(Integer courierId, List<Integer> orders, String date) {
        this.courierId = courierId;
        this.orders = orders;
        this.date = date;
    }
}
