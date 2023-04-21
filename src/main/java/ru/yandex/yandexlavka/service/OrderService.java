package ru.yandex.yandexlavka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.dto.OrderDTO;
import ru.yandex.yandexlavka.model.Order;
import ru.yandex.yandexlavka.repository.OrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {


    private OrderRepository orderRepository;
    private OrderDTOMapper orderDTOMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderDTOMapper orderDTOMapper) {
        this.orderRepository = orderRepository;
        this.orderDTOMapper = orderDTOMapper;
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream().map(orderDTOMapper)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(int orderID) {
        return orderRepository.findById(orderID)
                .map(orderDTOMapper)
                .orElse(null);

    }

    public void save() { //todo

    }

}
