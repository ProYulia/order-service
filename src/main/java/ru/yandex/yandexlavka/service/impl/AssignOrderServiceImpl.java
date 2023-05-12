package ru.yandex.yandexlavka.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.mapper.OrderMapper;
import ru.yandex.yandexlavka.model.dto.CouriersGroupsOrders;
import ru.yandex.yandexlavka.model.dto.GroupOrders;
import ru.yandex.yandexlavka.model.dto.OrderDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.model.response.OrderAssignResponse;
import ru.yandex.yandexlavka.repository.CourierRepository;
import ru.yandex.yandexlavka.repository.OrderRepository;
import ru.yandex.yandexlavka.service.AssignOrderService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssignOrderServiceImpl implements AssignOrderService {

    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;
    private final Map<Integer, List<Integer>> possibleAssignments = new HashMap<>();
    private final List<Integer> assignedOrders = new ArrayList<>();
    private List<CouriersGroupsOrders> couriersGroupsOrdersList = new ArrayList<>();
    private CourierEntity courier;



    public OrderAssignResponse assignOrdersToCouriers(String date) {
        getPotentialAssignments();
        for(Integer courierId : possibleAssignments.keySet()) {
            courier = courierRepository.findByCourierId(courierId);
            createBatch(possibleAssignments.get(courierId));
        }
        //couriersGroupsOrdersList.stream()
        return null;
    }

    private void addAssignmentsToDatabase() {
        for (CouriersGroupsOrders orderGroup : couriersGroupsOrdersList) {
            int courierId = orderGroup.getCourierId();
            courierRepository.addGroupOrders();
        }
    }

    private void getPotentialAssignments() {
        List<Integer[]> courierToOrder = orderRepository.getPotentialAssignments();
        for (Integer[] item : courierToOrder) {
            Integer orderId = item[0];
            Integer courierId = item[1];
            possibleAssignments.computeIfAbsent(courierId, k -> new ArrayList<>());
            possibleAssignments.get(courierId).add(orderId);
        }
    }

    private void createBatch(List<Integer> possibleOrders) {
        int maxWeight = 0;
        int maxCapacity = 0;
        int maxRegions = 0;

        switch (courier.getCourierType()) {
            case FOOT -> {
                maxWeight = 10;
                maxCapacity = 2;
                maxRegions = 1;
            }
            case BIKE -> {
                maxWeight = 20;
                maxCapacity = 4;
                maxRegions = 2;
            }
            case AUTO -> {
                maxWeight = 40;
                maxCapacity = 7;
                maxRegions = 3;
            }
        }

        int weightLeft = maxWeight;
        int courierMaxDeliveries = calculateMaxDeliveries();
        int slotsLeft = courierMaxDeliveries * maxCapacity;

        List<GroupOrders> groupOrders = new ArrayList<>();
        List<OrderDto> batch = new ArrayList<>(maxCapacity);
        List<Integer> currentRegions = new ArrayList<>(maxRegions);

        for(Integer orderId : possibleOrders) {
            OrderEntity order = orderRepository.findByOrderId(orderId);
            if (assignedOrders.contains(orderId)) continue;
            if (order.getWeight() > weightLeft) continue;
            if (currentRegions.size() == maxRegions && !currentRegions.contains(order.getRegion())) continue;
            if (courierMaxDeliveries == slotsLeft) return;

            if (batch.size() == maxCapacity) {
                groupOrders.add(new GroupOrders(batch));
                batch = new ArrayList<>(maxCapacity);
            }

            batch.add(orderMapper.orderEntityToDto(order));
            slotsLeft -= 1;
            if (!currentRegions.contains(order.getRegion())) {
                currentRegions.add(order.getRegion());
            }
            weightLeft -= order.getWeight();
            assignedOrders.add(orderId);
        }
        groupOrders.add(new GroupOrders(batch));
        couriersGroupsOrdersList.add(new CouriersGroupsOrders(courier.getCourierId(), groupOrders));

        courier.setOrderGroups();
    }


    private int calculateMaxDeliveries() {
        List<String> workingHours = courier.getWorkingHours();
        int maxSlots = 0;
        int deliveryDuration = 0;
        switch (courier.getCourierType()) {
            case FOOT -> deliveryDuration = 35;
            case BIKE -> deliveryDuration = 20;
            case AUTO -> deliveryDuration = 12;
        }

        for(String shift : workingHours) {
            Instant startTime = Instant.parse(shift.substring(0, 6));
            Instant endTime = Instant.parse(shift.substring(6, 11));
            long totalTime = ChronoUnit.MINUTES.between(startTime, endTime);
            while(totalTime >= 0) {
                maxSlots++;
                totalTime -= deliveryDuration;
            }
        }
        return maxSlots;
    }


}




