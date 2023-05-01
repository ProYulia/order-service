package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.yandexlavka.model.dto.CourierDto;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;

@Mapper(componentModel = "spring")
public interface CourierMapper {

    CreateCourierDto courierToDto(CourierEntity courierEntity);


    @Mapping(target = "courierID", ignore = true)
    CourierEntity courierDtoToEntity(CreateCourierDto createCourierDto);


    @Mapping(target = "id", source = "courierID")
    CourierDto entityToCreateResponse(CourierEntity courierEntity);

}
