package ru.yandex.yandexlavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.dto.OrderDto;
import ru.yandex.yandexlavka.model.request.CreateOrderRequest;
import ru.yandex.yandexlavka.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public List<OrderDto> getAllOrders(@RequestParam(name = "offset", required = false) Integer offset,
                                       @RequestParam(name = "limit", required = false) Integer limit) {

        return orderService.getAllOrders(offset, limit);
    }

    @GetMapping("/{orderID}")
    public OrderDto getOrderByID(@PathVariable int orderID) {
        return orderService.getOrderById(orderID);
    }

    @PostMapping()
    public List<OrderDto> addOrders(@RequestBody CreateOrderRequest createOrder) {

        List<CreateOrderDto> createOrderDtoList = createOrder.getCreateOrderDtoList();
        return orderService.saveAllOrders(createOrderDtoList);
    }

//    @PatchMapping("/complete") //TODO Обработчик должен быть идемпотентным
//    public List<OrderEntity> postCompletedOrders(@RequestBody List<CompletedOrderDto> completedOrders) {
//        return orderService.completeOrders(completedOrders);
//    }

}
