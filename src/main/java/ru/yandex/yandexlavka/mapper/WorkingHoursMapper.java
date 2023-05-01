package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.yandexlavka.model.entity.WorkingHoursEntity;

@Mapper(componentModel = "spring")
public interface WorkingHoursMapper {

    @Mapping(target = "startTime", dateFormat = "HH:mm")
    @Mapping(target = "endTime", dateFormat = "HH:mm")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courier", ignore = true)
    WorkingHoursEntity stringToWorkingHoursEntity(String startTime, String endTime);


}
