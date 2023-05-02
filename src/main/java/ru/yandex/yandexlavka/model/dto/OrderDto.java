package ru.yandex.yandexlavka.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class OrderDto {

    @JsonProperty(value = "order_id")
    private Integer id;

    @JsonProperty(value = "weight")
    private Float weight;

    @JsonProperty(value = "regions")
    private Integer region;

    @JsonProperty(value = "delivery_hours")
    private List<String> deliveryHours;

    @JsonProperty(value = "cost")
    private Integer cost;

    @JsonProperty(value = "completed_time")
    private Instant completeTime;

}
