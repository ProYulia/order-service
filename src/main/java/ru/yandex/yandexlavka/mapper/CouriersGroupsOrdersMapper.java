package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.yandex.yandexlavka.model.dto.CouriersGroupsOrders;
import ru.yandex.yandexlavka.model.dto.GroupOrders;
import ru.yandex.yandexlavka.model.dto.OrderDto;
import ru.yandex.yandexlavka.model.entity.CouriersGroupsOrdersEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CouriersGroupsOrdersMapper {
    @Mapping(target = "orders", source = "orderGroups")
    CouriersGroupsOrders couriersGroupsOrdersToDto(Integer courierId, List<GroupOrders> orderGroups);

    @Mapping(target = "orders", source = "orderDtoList")
    GroupOrders toGroupOrders(CouriersGroupsOrdersEntity entity,
                              List<OrderDto> orderDtoList);
}
