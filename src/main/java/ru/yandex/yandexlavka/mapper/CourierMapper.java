package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;

@Mapper(componentModel = "spring")
public interface CourierMapper {

    @Mapping(
            target = "regions",
            expression =
             "java(courierEntity.getRegionEntities().stream().map(RegionEntity::getRegionNumber).collect(java.util.stream.Collectors.toList())"
    )
    @Mapping(target = "workingHours",
    expression =
            "java(courierEntity.shiftEntitiesToList())"
    )
    CreateCourierDto courierToDto(CourierEntity courierEntity);


    @Mapping(target = "courierID", ignore = true)
    @Mapping(source = "regions", target = "regionEntities")
    @Mapping(source = "workingHours", target = "shiftEntities")
    CourierEntity courierDtoToEntity(CreateCourierDto createCourierDto);


}
