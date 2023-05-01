package ru.yandex.yandexlavka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.mapper.OrderMapper;
import ru.yandex.yandexlavka.model.dto.CompletedOrderDto;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.model.entity.RegionEntity;
import ru.yandex.yandexlavka.repository.OrderRepository;
import ru.yandex.yandexlavka.repository.RegionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RegionRepository regionRepository;



    public List<CreateOrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream().map(orderEntity -> orderMapper.orderToOrderDto(orderEntity, orderEntity.getDeliveryHours()))
                .collect(Collectors.toList());
    }

    public CreateOrderDto getOrderById(int orderID) {
        OrderEntity orderEntity = orderRepository.findById(orderID).orElseThrow();
         return orderMapper.orderToOrderDto(orderEntity, orderEntity.getDeliveryHours());
    }


    public List<OrderEntity> saveAllOrders(List<CreateOrderDto> orders) { //should I return the result here?
        List<OrderEntity> orderEntityList = new ArrayList<>();
        for(CreateOrderDto orderDto : orders) {
            RegionEntity regionEntity = regionRepository.findRegionEntityByRegionNumber(
                    orderDto.getRegion())
                    .orElseThrow();
            OrderEntity orderEntity = orderMapper.orderDtotoOrderEntity(orderDto, regionEntity);
            orderEntityList.add(orderEntity);
        }
        orderRepository.saveAll(orderEntityList);
        return orderEntityList;
    }

    public List<OrderEntity> completeOrders(List<CompletedOrderDto> completedOrders) { //todo
        return new ArrayList<>();
    }


}
