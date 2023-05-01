//package ru.yandex.yandexlavka.mapper;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
//import ru.yandex.yandexlavka.model.entity.DeliveryHoursEntity;
//import ru.yandex.yandexlavka.model.entity.OrderEntity;
//import ru.yandex.yandexlavka.model.entity.RegionEntity;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface OrderMapper {
//
//
//    @Mapping(source = "orderEntity.regionEntity.regionNumber", target = "region")
//    @Mapping(expression =
//            "java(deliveryHoursList.stream().map(DeliveryHoursEntity::toString).toList();)",
//            target = "deliveryHours")
//
//    CreateOrderDto orderToOrderDto(OrderEntity orderEntity, List<DeliveryHoursEntity> deliveryHoursList);
//
//
//
//    @Mapping(source = "region", target = "regionEntity")
//    @Mapping(target = "orderId", ignore = true)
//    @Mapping(target = "deliveryHours", ignore = true)
//    @Mapping(target = "completeTime", ignore = true)
//    OrderEntity orderDtotoOrderEntity(CreateOrderDto createOrderDto, RegionEntity region);
//
//
//}
