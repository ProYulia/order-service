package ru.yandex.yandexlavka.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.yandex.yandexlavka.model.entity.CourierEntity;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourierDto {

    @NotNull
    @JsonProperty(value = "courier_type", required = true)
    private CourierEntity.CourierType courierType;

    @NotNull
    @JsonProperty(value = "regions", required = true)
    private List<Integer> regions;

    @NotNull
    @JsonProperty(value = "working_hours", required = true)
    private List<String> workingHours;

}
