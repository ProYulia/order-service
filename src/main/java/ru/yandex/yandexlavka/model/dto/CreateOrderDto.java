package ru.yandex.yandexlavka.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CreateOrderDto {

    @JsonProperty(value = "weight", required = true)
    private Float weight;

    @JsonProperty(value = "regions", required = true)
    private Integer region;

    @JsonProperty(value = "delivery_hours", required = true)
    private List<String> deliveryHours;

    @JsonProperty(value = "cost", required = true)
    private Integer cost;

}
