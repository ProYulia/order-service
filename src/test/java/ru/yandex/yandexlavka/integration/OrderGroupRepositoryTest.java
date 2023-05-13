package ru.yandex.yandexlavka.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.model.entity.OrderGroupEntity;
import ru.yandex.yandexlavka.repository.CourierRepository;
import ru.yandex.yandexlavka.repository.OrderRepository;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderGroupRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CourierRepository courierRepository;
    
    @AfterEach
    public void cleanup() {
        courierRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void shouldSaveOrderGroup() {
        var orders = orderRepository.saveAll(List.of(
            getRandomOrderEntity(),
            getRandomOrderEntity()
        ));
        CourierEntity randomCourierEntity = getRandomCourierEntity();
        var courier = courierRepository.save(randomCourierEntity);
        
        var orderGroup = new OrderGroupEntity(courier, orders);
        
        courier.setOrderGroupEntities(Set.of(orderGroup));
        
        courierRepository.save(courier);
        
        var savedCourier = courierRepository.findByCourierId(courier.getCourierId());

        OrderGroupEntity orderGroupEntity = savedCourier.getOrderGroupEntities().stream().findFirst().orElseThrow();
        
        assertEquals(1, savedCourier.getOrderGroupEntities().size());
        assertEquals(2, orderGroupEntity.getOrders().size());
        assertEquals(1, orderGroupEntity.getGroupId());
    }

    private OrderEntity getRandomOrderEntity() {
        var result = new OrderEntity();
        result.setCost(ThreadLocalRandom.current().nextInt(100));
        result.setWeight(ThreadLocalRandom.current().nextFloat(100));
        result.setRegion(ThreadLocalRandom.current().nextInt(100));
        result.setCompleteTime(Instant.now());
        result.setDeliveryHours(List.of("10:00-10:30"));

        return result;
    }

    private CourierEntity getRandomCourierEntity() {
        var result = new CourierEntity();
        result.setCourierType(CourierEntity.CourierType.FOOT);
        result.setRegions(List.of(10));
        result.setWorkingHours(List.of("10:00-18:00"));

        return result;
    }
}
