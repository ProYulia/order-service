package ru.yandex.yandexlavka.model.dto;

import lombok.*;
import ru.yandex.yandexlavka.model.entity.CourierEntity;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CreateCourierDto {

    private CourierEntity.CourierType courierType; // enum vs String
    private List<Integer> regions;
    private List<String> workingHours;


}
