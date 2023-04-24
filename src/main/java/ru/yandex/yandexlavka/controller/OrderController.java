package ru.yandex.yandexlavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public List<CreateOrderDto> getAllOrders() { //offset+limit
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderID}")
    public CreateOrderDto getOrderByID(@PathVariable int orderID) {
        return orderService.getOrderById(orderID);
    }

//    @PostMapping() todo add createOrderRequest
//    public OrderEntity addOrders(@RequestBody CreateOrderDto createOrderDto) {
//        return orderService.saveOrder(createOrderDto);
//    }
    @PatchMapping("/complete") //TODO
    public String postCompletedOrders(@RequestBody int courierID, int orderID, String completeTime) {
        return "";
    }

//    public String postAssignOrder() {return "";} optional



}
