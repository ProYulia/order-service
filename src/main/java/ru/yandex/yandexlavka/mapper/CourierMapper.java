package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.model.entity.RegionEntity;
import ru.yandex.yandexlavka.model.entity.WorkingHoursEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourierMapper {

    @Mapping(
            target = "regions",
            expression =
             "java(courierEntity.getRegionEntities().stream().map(RegionEntity::getRegionNumber).toList();)"
    )
    @Mapping(target = "workingHours",
    expression =
            "java(courierEntity.getWorkingHours().stream().map(el -> el.toString()).toList();)"
    )
    CreateCourierDto courierToDto(CourierEntity courierEntity);


    @Mapping(target = "courierID", ignore = true)
    @Mapping(source = "regions", target = "regionEntities")
    @Mapping(source = "workingHoursEntities", target = "workingHours")
    CourierEntity courierDtoToEntity(CreateCourierDto createCourierDto,
                                     List<RegionEntity> regions,
                                     List<WorkingHoursEntity> workingHoursEntities);

}
