package ru.yandex.yandexlavka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.model.dto.CourierDto;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.repository.CourierRepository;
import ru.yandex.yandexlavka.repository.OrderRepository;

import java.util.List;

@Service
public class AssignOrderService {

    @Autowired
    private CourierService courierService;

    @Autowired
    CourierRepository courierRepository;

    @Autowired
    OrderRepository orderRepository;


    public void assignOrderToCourier(List<OrderEntity> orderEntityList) {

        int limit = courierRepository.lastCourierId();

        List<CourierDto> couriers = courierService.getAllCouriers(0, limit).getCouriers();

        for (OrderEntity order : orderEntityList) {
            int orderRegion = order.getRegion();
            int orderId = order.getOrderId();

            for (CourierDto courier : couriers) {
                List<Integer> courierRegions = courier.getRegions();
                Integer courierId = courier.getId();

                if (courierRegions.contains(orderRegion)) {
                    orderRepository.updateOrderEntityByOrderId(orderId, courierId);
                    break;
                }
            }
        }
    }



}
