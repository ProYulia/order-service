package ru.yandex.yandexlavka.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;
import ru.yandex.yandexlavka.model.dto.OrderDto;
import ru.yandex.yandexlavka.model.request.CompleteOrderRequest;
import ru.yandex.yandexlavka.model.request.CreateOrderRequest;
import ru.yandex.yandexlavka.model.response.OrderAssignResponse;
import ru.yandex.yandexlavka.service.AssignOrderService;
import ru.yandex.yandexlavka.service.OrderService;

import java.util.List;

import static ru.yandex.yandexlavka.controller.ConstantList.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final AssignOrderService assignOrderService;

    @GetMapping(ORDER_BASE_PATH)
    public List<OrderDto> getAllOrders(@RequestParam(name = "offset", required = false) Integer offset,
                                       @RequestParam(name = "limit", required = false) Integer limit) {

        return orderService.getAllOrders(offset, limit);
    }

    @PostMapping(ORDER_BASE_PATH)
    @RateLimiter(name = "rateLimiterApi")
    public List<OrderDto> addOrders(@Valid @RequestBody CreateOrderRequest createOrder) {

        List<CreateOrderDto> createOrderDtoList = createOrder.getCreateOrderDtoList();
        return orderService.saveAllOrders(createOrderDtoList);
    }

    @PostMapping(COMPLETE_ORDER_PATH) //TODO Обработчик должен быть идемпотентным
    @RateLimiter(name = "rateLimiterApi")
    public List<OrderDto> completeOrders(@RequestBody CompleteOrderRequest completedOrders) {

        return orderService.completeOrders(completedOrders);
    }

    @PostMapping(ASSIGN_ORDERS_PATH)
    @RateLimiter(name = "rateLimiterApi")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderAssignResponse assignOrders(@RequestParam(required = false, name = "date") String date) {

        return assignOrderService.assignOrdersToCouriers(date);
    }

    @GetMapping(SINGLE_ORDER_PATH)
    @RateLimiter(name = "rateLimiterApi")
    public OrderDto getOrderByID(@PathVariable(name = "order_id") Integer orderId) {
        return orderService.getOrderById(orderId);
    }


}
