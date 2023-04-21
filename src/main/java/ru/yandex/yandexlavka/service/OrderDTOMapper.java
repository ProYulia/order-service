package ru.yandex.yandexlavka.service;

import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.dto.OrderDTO;
import ru.yandex.yandexlavka.model.Order;

import java.util.function.Function;

@Service
public class OrderDTOMapper implements Function<Order, OrderDTO> {
    @Override
    public OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getOrderId(),
                order.getWeight(),
                order.getRegion().getRegionNumber(),
                order.getDeliveryHours(),
                order.getCost(),
                order.getCompleteTime()
        );
    }
}
