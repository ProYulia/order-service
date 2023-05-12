package ru.yandex.yandexlavka.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.mapper.OrderMapper;
import ru.yandex.yandexlavka.model.dto.CompletedOrderDto;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.dto.OrderDto;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.model.request.CompleteOrderRequest;
import ru.yandex.yandexlavka.repository.OrderRepository;
import ru.yandex.yandexlavka.service.OrderService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

   // private final AssignOrderServiceImpl assignOrderServiceImpl; //todo


    public List<OrderDto> getAllOrders(Integer offset, Integer limit) {

        if (offset == null) offset = 0;
        else if (offset < 0) throw new IllegalArgumentException();

        if (limit == null) limit = 1;
        else if (limit < 0) throw new IllegalArgumentException();

        List<OrderDto> orderDtoList = orderRepository
                .findAll(offset, limit)
                .stream()
                .map(orderMapper::orderEntityToDto)
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

        return orderEntityList
                .stream()
                .map(orderMapper::orderEntityToDto)
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
            //completeTime.minus(25, ChronoUnit.MINUTES)

            int entitiesModified = orderRepository.updateByOrderId(orderId, courierId, completeTime);
            if (entitiesModified == 0) throw new RuntimeException(); //todo

            OrderEntity entity = orderRepository.findByOrderId(orderId);
            orderDtoList.add(orderMapper.orderEntityToDto(entity));
        }

        return orderDtoList;
    }


}
