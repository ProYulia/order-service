package ru.yandex.yandexlavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.dto.OrderDTO;
import ru.yandex.yandexlavka.model.Order;
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
    public List<OrderDTO> getAllOrders() { //offset+limit
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderID}")
    public OrderDTO getOrderByID(@PathVariable int orderID) {
        return orderService.getOrderById(orderID);
    }

    @PostMapping() //TODO
    public String addOrders(@RequestBody OrderDTO orderDTO) {

        orderService.save();
        return "";
    }
    @PatchMapping("/complete") //TODO
    public String postCompletedOrders(@RequestBody int courierID, int orderID, String completeTime) {
        return "";
    }

//    public String postAssignOrder() {return "";} optional



}
