package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.dto.OrderDto;
import ru.yandex.yandexlavka.model.request.CompleteOrderRequest;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders(Integer offset, Integer limit);

    OrderDto getOrderById(int orderID);

    List<OrderDto> saveAllOrders(List<CreateOrderDto> createOrderDtoList);

    List<OrderDto> completeOrders(CompleteOrderRequest completedOrders);
}
