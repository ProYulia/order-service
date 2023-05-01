package ru.yandex.yandexlavka.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.yandex.yandexlavka.model.entity.CourierEntity;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CourierDto {

    @JsonProperty(value = "courier_id")
    private Integer id;

    @JsonProperty(value = "courier_type")
    private CourierEntity.CourierType courierType;

    @JsonProperty(value = "regions")
    private List<Integer> regions;

    @JsonProperty(value = "working_hours")
    private List<String> workingHours;
}
