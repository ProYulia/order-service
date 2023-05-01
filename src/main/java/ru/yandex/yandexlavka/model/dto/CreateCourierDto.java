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
public class CreateCourierDto {

    @JsonProperty(value = "courier_type", required = true)
    private CourierEntity.CourierType courierType; // enum vs String

    @JsonProperty(value = "regions", required = true)
    private List<Integer> regions;

    @JsonProperty(value = "working_hours", required = true)
    private List<String> workingHours;

}
