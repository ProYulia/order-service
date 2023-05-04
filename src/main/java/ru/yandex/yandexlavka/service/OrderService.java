package ru.yandex.yandexlavka.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.exception.BadRequestResponse;
import ru.yandex.yandexlavka.mapper.OrderMapper;
import ru.yandex.yandexlavka.model.dto.CompletedOrderDto;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.dto.OrderDto;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.model.request.CompleteOrderRequest;
import ru.yandex.yandexlavka.repository.OrderRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AssignOrderService assignOrderService;

    @Transactional
    public List<OrderDto> getAllOrders(Integer offset, Integer limit) {

        if (offset == null) offset = 0;
        else if (offset < 0) throw new IllegalArgumentException();

        if (limit == null) limit = 1;
        else if (limit < 0) throw new IllegalArgumentException();

        List<OrderDto> orderDtoList = orderRepository
                .findAll(offset, limit)
                .stream()
                .map(entity -> orderMapper.orderEntityToDto(entity))
                .toList();

        return orderDtoList;
    }

    public OrderDto getOrderById(int orderID) { //wrap with try-catch
        OrderEntity orderEntity = orderRepository.findById(orderID).orElseThrow();
         return orderMapper.orderEntityToDto(orderEntity);
    }


    @Transactional
    public List<OrderDto> saveAllOrders(List<CreateOrderDto> createOrderDtoList) {

        List<OrderEntity> orderEntityList = new ArrayList<>();

        for(CreateOrderDto orderDto : createOrderDtoList) {
            OrderEntity orderEntity = orderMapper.orderDtoToEntity(orderDto);
            orderEntityList.add(orderEntity);
        }

        orderRepository.saveAll(orderEntityList);
        assignOrderService.assignOrderToCourier(orderEntityList); //todo

        return orderEntityList
                .stream()
                .map(entity -> orderMapper.orderEntityToDto(entity))
                .toList();
    }

    @Transactional
    public List<OrderDto> completeOrders(CompleteOrderRequest completedOrders) { //todo

        List<CompletedOrderDto> completedOrderDtoList = completedOrders.getCompletedOrderDtoList();
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (CompletedOrderDto order : completedOrderDtoList) {

            Integer orderId = order.getOrderId();
            Integer courierId = order.getCourierId();
            Instant completeTime = Instant.parse(order.getCompleteTime());

            int entitiesModified = orderRepository.updateByOrderId(orderId, courierId, completeTime);
            if (entitiesModified == 0) throw new RuntimeException(); //todo

            OrderEntity entity = orderRepository.findByOrderId(orderId);
            orderDtoList.add(orderMapper.orderEntityToDto(entity));
        }

        return orderDtoList;
    }


}
