package ru.yandex.yandexlavka.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class GroupOrders {

    @JsonProperty(value = "group_order_id")
    private Integer groupId;

    @JsonProperty(value = "orders")
    private List<OrderDto> orders;

}
