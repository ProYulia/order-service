package ru.yandex.yandexlavka.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {

    @NotNull
    @JsonProperty(value = "weight", required = true)
    private Float weight;

    @NotNull
    @JsonProperty(value = "regions", required = true)
    private Integer region;

    @NotNull
    @JsonProperty(value = "delivery_hours", required = true)
    private List<String> deliveryHours;

    @NotNull
    @JsonProperty(value = "cost", required = true)
    private Integer cost;

}
