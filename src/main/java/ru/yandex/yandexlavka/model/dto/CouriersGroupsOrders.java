package ru.yandex.yandexlavka.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CouriersGroupsOrders {

    private Integer courierId;
    private List<GroupOrders> orderGroups;

}
