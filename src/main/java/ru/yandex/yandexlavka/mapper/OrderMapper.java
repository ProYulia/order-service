package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.model.entity.RegionEntity;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "weight", target = "weight")
    @Mapping(source = "regionEntity.regionNumber", target = "region")
    CreateOrderDto orderToOrderDto(OrderEntity orderEntity);


    @Mapping(source = "region", target = "regionEntity")
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "deliveryHours", ignore = true)
    OrderEntity orderDtotoOrderEntity(CreateOrderDto createOrderDto, RegionEntity region);


}
