package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.yandexlavka.model.dto.CourierDto;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.model.response.GetCourierMetaInfoResponse;

@Mapper(componentModel = "spring")
public interface CourierMapper {

    CreateCourierDto courierToDto(CourierEntity courierEntity);


    @Mapping(target = "courierId", ignore = true)
    CourierEntity courierDtoToEntity(CreateCourierDto createCourierDto);


    @Mapping(target = "id", source = "courierId")
    CourierDto entityToCreateResponse(CourierEntity courierEntity);

    @Mapping(target = "type", source = "courierType")
    @Mapping(target = "rating", source = "rating")
    @Mapping(target = "earnings", source = "earnings")
    GetCourierMetaInfoResponse entityToMetaInfoResponse(CourierEntity courierEntity,
                                                        Integer rating,
                                                        Integer earnings,
                                                        String courierType);


    //CouriersGroupsOrders entityToCouriersGroupOrders(CourierEntity courierEntity);

}
