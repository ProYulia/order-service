package ru.yandex.yandexlavka.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ru.yandex.yandexlavka.model.entity.CourierEntity;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateCourierDto {

    @NotNull
    @JsonProperty(value = "courier_type", required = true)
    private CourierEntity.CourierType courierType;

    @JsonProperty(value = "regions", required = true)
    private List<Integer> regions;

    @JsonProperty(value = "working_hours", required = true)
    private List<String> workingHours;

}
