package ru.yandex.yandexlavka.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CouriersGroupsOrders {

    private Integer courierId;
    private List<GroupOrders> orders;

}
