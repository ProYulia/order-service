package ru.yandex.yandexlavka.model.dto;

import lombok.*;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.model.entity.RegionEntity;
import ru.yandex.yandexlavka.model.entity.ShiftEntity;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CreateCourierDto {

    private CourierEntity.CourierType courierType;
//    private List<RegionEntity> regionEntities;
//    private List<ShiftEntity> shiftEntities;

    private List<Integer> regions;
    private List<String> workingHours;


}
