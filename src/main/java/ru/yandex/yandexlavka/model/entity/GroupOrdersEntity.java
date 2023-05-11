package ru.yandex.yandexlavka.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "order_groups")
@Getter
@Setter
@NoArgsConstructor
public class GroupOrdersEntity {

    @Id
    @SequenceGenerator(name="groupsSequence", sequenceName="groups_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="groupsSequence")
    @Column(name = "group_id")
    private Integer groupId;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderEntity> orders;
}
