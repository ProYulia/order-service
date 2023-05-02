package ru.yandex.yandexlavka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.mapper.OrderMapper;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.dto.OrderDto;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;


    public List<OrderDto> getAllOrders(Integer offset, Integer limit) {

        if (offset == null) offset = 0;
        else if (offset < 0) throw new IllegalArgumentException();

        if (limit == null) limit = 1;
        else if (limit < 0) throw new IllegalArgumentException();

        List<OrderDto> orderDtoList = orderRepository
                .findAll(offset, limit)
                .stream()
                .map(entity -> orderMapper.orderEntityToOrderDto(entity))
                .toList();

        return orderDtoList;
    }

    public OrderDto getOrderById(int orderID) {
        OrderEntity orderEntity = orderRepository.findById(orderID).orElseThrow();
         return orderMapper.orderEntityToOrderDto(orderEntity);
    }


    public List<OrderDto> saveAllOrders(List<CreateOrderDto> createOrderDtoList) {

        List<OrderEntity> orderEntityList = new ArrayList<>();

        for(CreateOrderDto orderDto : createOrderDtoList) {
            OrderEntity orderEntity = orderMapper.createOrderDtoToEntity(orderDto);
            orderEntityList.add(orderEntity);
        }

        orderRepository.saveAll(orderEntityList);

        return orderEntityList
                .stream()
                .map(entity -> orderMapper.orderEntityToOrderDto(entity))
                .toList();
    }

//    public List<OrderEntity> completeOrders(List<CompletedOrderDto> completedOrders) { //todo
//        return new ArrayList<>();
//    }


}
