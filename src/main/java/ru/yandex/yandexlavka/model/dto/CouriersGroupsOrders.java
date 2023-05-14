package ru.yandex.yandexlavka.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CouriersGroupsOrders {

    @JsonProperty(value = "courier_id")
    private Integer courierId;

    @JsonProperty(value = "orders")
    private List<GroupOrders> orders;

}
