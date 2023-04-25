package ru.yandex.yandexlavka.integration;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.service.OrderService;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void shouldGetOrders() {
        List<CreateOrderDto> allOrders = orderService.getAllOrders();
        Assertions.assertEquals(Collections.EMPTY_LIST, allOrders);
    }

    @Test
    public void shouldCreateOrders(CreateOrderDto[] orders) {
        List<OrderEntity> orderEntityList = orderService.saveAllOrders(orders);
        Assertions.assertEquals(Collections.EMPTY_LIST, orderEntityList);

    }

    @Test
    public void shouldGetOrderById() {
        CreateOrderDto orderDto = orderService.getOrderById(1);
        Assertions.assertNull(orderDto);
    }
}
