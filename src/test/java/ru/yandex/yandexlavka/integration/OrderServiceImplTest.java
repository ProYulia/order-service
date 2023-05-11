package ru.yandex.yandexlavka.integration;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.repository.OrderRepository;
import ru.yandex.yandexlavka.service.impl.CourierServiceImpl;
import ru.yandex.yandexlavka.service.impl.OrderServiceImpl;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private CourierServiceImpl courierServiceImpl;

    @Autowired
    private OrderRepository orderRepository;

//    @Test
//    public void shouldGetOrders() {
////        List<CreateOrderDto> allOrders = orderService.getAllOrders();
////        Assertions.assertEquals(Collections.EMPTY_LIST, allOrders);
//    }

//    @Test
//    public void shouldCreateOrders() {
//        //given
//        List<CreateOrderDto> createOrderDtos = List.of(new CreateOrderDto(1.1F,
//                0,
//                List.of("15:00-17:00"),
//                100));
//
//        //when
//        List<OrderEntity> orderEntities = orderService.saveAllOrders(createOrderDtos);
//        List<OrderEntity> orderEntityList = orderRepository.findAll();
//
//        //then
//        Assertions.assertEquals(2, orderEntityList.size());
//    }

    @Test
    public void shouldGetOrderById() {
        //given
        int id = 1;

        //when
        //CreateOrderDto orderDto = orderService.getOrderById(id);

        //then
        //Assertions.assertEquals("10:00-12:00", orderDto.getDeliveryHours().get(0));
    }

    @BeforeEach
    public void dropAll() {
        //creating a courier in order to have some regions available
        List<CreateCourierDto> createCourierDto = List.of(new CreateCourierDto(CourierEntity.CourierType.FOOT,
                Arrays.asList(0, 1),
                List.of("10:00-18:00")));

        courierServiceImpl.saveCouriers(createCourierDto);

        // creating an order
        List<CreateOrderDto> createOrderDtos = List.of(new CreateOrderDto(1.1F,
                0,
                List.of("10:00-12:00"),
                100));

        orderServiceImpl.saveAllOrders(createOrderDtos);
    }
}
