package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.dto.OrderDto;
import ru.yandex.yandexlavka.model.entity.OrderEntity;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", source = "orderId")
    OrderDto orderEntityToDto(OrderEntity orderEntity);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "completeTime", ignore = true)
    @Mapping(target = "groupId", ignore = true)
    OrderEntity orderDtoToEntity(CreateOrderDto createOrderDto);

}
