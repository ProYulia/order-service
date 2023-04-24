package ru.yandex.yandexlavka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.mapper.OrderMapper;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.model.entity.RegionEntity;
import ru.yandex.yandexlavka.repository.OrderRepository;
import ru.yandex.yandexlavka.repository.RegionRepository;

import java.util.List;
import java.util.Optional;
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
                .stream().map(orderEntity -> orderMapper.orderToOrderDto(orderEntity))
                .collect(Collectors.toList());
    }

    public CreateOrderDto getOrderById(int orderID) {
        OrderEntity orderEntity = orderRepository.findById(orderID).orElseThrow();
         return orderMapper.orderToOrderDto(orderEntity);
    }

    public void saveOrder(CreateOrderDto createOrderDto) {

        RegionEntity regionEntity = regionRepository.findRegionEntityByRegionNumber(createOrderDto
                .getRegion())
                .orElseThrow();
        OrderEntity orderResult = orderMapper.orderDtotoOrderEntity(createOrderDto, regionEntity);
        orderRepository.save(orderResult);
    }

}
