package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.yandexlavka.model.entity.WorkingHoursEntity;

@Mapper
public interface WorkingHoursMapper {

    @Mapping(expression = "java(LocalTime.parse(workingHours.substring(1, 6)))", target = "startTime")
    @Mapping(expression = "java(LocalTime.parse(workingHours.substring(6, 11)))", target = "endTime")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courier", ignore = true)
    WorkingHoursEntity stringToWorkingHoursEntity(String workingHours);
}
