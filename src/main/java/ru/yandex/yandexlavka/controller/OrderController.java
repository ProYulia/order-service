package ru.yandex.yandexlavka.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.dto.OrderDto;
import ru.yandex.yandexlavka.model.request.CompleteOrderRequest;
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
    @RateLimiter(name = "rateLimiterApi")
    public OrderDto getOrderByID(@PathVariable int orderID) {
        return orderService.getOrderById(orderID);
    }

    @PostMapping()
    @RateLimiter(name = "rateLimiterApi")
    public List<OrderDto> addOrders(@RequestBody CreateOrderRequest createOrder) {

        List<CreateOrderDto> createOrderDtoList = createOrder.getCreateOrderDtoList();
        return orderService.saveAllOrders(createOrderDtoList);
    }

    @PostMapping("/complete") //TODO Обработчик должен быть идемпотентным
    @RateLimiter(name = "rateLimiterApi")
    public List<OrderDto> completeOrders(@RequestBody CompleteOrderRequest completedOrders) {
        return orderService.completeOrders(completedOrders);
    }

}
