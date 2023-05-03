package ru.yandex.yandexlavka.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CompletedOrderDto {

    @JsonProperty(value = "courier_id", required = true)
    private Integer courierId;

    @JsonProperty(value = "order_id", required = true)
    private Integer orderId;

    @JsonProperty(value = "complete_time", required = true)
    private String completeTime;
}
