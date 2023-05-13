package ru.yandex.yandexlavka.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.mapper.CouriersGroupsOrdersMapper;
import ru.yandex.yandexlavka.mapper.OrderMapper;
import ru.yandex.yandexlavka.model.dto.CouriersGroupsOrders;
import ru.yandex.yandexlavka.model.dto.GroupOrders;
import ru.yandex.yandexlavka.model.dto.OrderDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.model.entity.CouriersGroupsOrdersEntity;
import ru.yandex.yandexlavka.model.entity.OrderEntity;
import ru.yandex.yandexlavka.model.response.OrderAssignResponse;
import ru.yandex.yandexlavka.repository.CourierRepository;
import ru.yandex.yandexlavka.repository.CouriersGroupOrdersRepository;
import ru.yandex.yandexlavka.repository.OrderRepository;
import ru.yandex.yandexlavka.service.AssignOrderService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AssignOrderServiceImpl implements AssignOrderService {

    private final CourierRepository courierRepository;

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final CouriersGroupsOrdersMapper couriersGroupsOrdersMapper;

    private final CouriersGroupOrdersRepository couriersGroupOrdersRepository;
    private final Map<Integer, List<Integer>> possibleAssignments = new HashMap<>();
    private final List<Integer> assignedOrders = new ArrayList<>();
    private List<CouriersGroupsOrdersEntity> couriersGroupsOrdersList = new ArrayList<>();
    private CourierEntity courier;



    public OrderAssignResponse assignOrdersToCouriers(String date) {
        getPotentialAssignments();
        for(Integer courierId : possibleAssignments.keySet()) {
            courier = courierRepository.findByCourierId(courierId);
            createBatch(possibleAssignments.get(courierId));
        }

        List<CouriersGroupsOrdersEntity> couriersGroupsOrdersEntities = couriersGroupOrdersRepository
                .saveAll(couriersGroupsOrdersList);

        Map<Integer, List<GroupOrders>> courierMap = new HashMap<>();

        for (CouriersGroupsOrdersEntity groupEntity : couriersGroupsOrdersEntities) {
            List<Integer> orderIdList = groupEntity.getOrders();
            List<OrderEntity> orderEntityList = orderIdList.stream().map(orderRepository::findByOrderId).toList();
            List<OrderDto> orderDtoList = orderEntityList.stream().map(orderMapper::orderEntityToDto).toList();
            GroupOrders groupOrders = couriersGroupsOrdersMapper.toGroupOrders(groupEntity, orderDtoList);

            if(!courierMap.containsKey(groupEntity.getCourierId())) {
                courierMap.put(groupEntity.getCourierId(), new ArrayList<>());
            }
            courierMap.get(groupEntity.getCourierId()).add(groupOrders);
        }

        List<CouriersGroupsOrders> couriersGroupsOrders = new ArrayList<>();
        for(Map.Entry<Integer, List<GroupOrders>> entry : courierMap.entrySet()) {
            couriersGroupsOrders.add(couriersGroupsOrdersMapper.couriersGroupsOrdersToDto(entry.getKey(), entry.getValue()));
        }

        if(date == null) {
            date = LocalDate.now().toString();
        }

        return new OrderAssignResponse(date, couriersGroupsOrders);
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

        List<Integer> orderBatch = new ArrayList<>(maxCapacity);
        List<Integer> currentRegions = new ArrayList<>(maxRegions);

        for(Integer orderId : possibleOrders) {
            OrderEntity order = orderRepository.findByOrderId(orderId);
            if (assignedOrders.contains(orderId)) continue;
            if (order.getWeight() > weightLeft) continue;
            if (currentRegions.size() == maxRegions && !currentRegions.contains(order.getRegion())) continue;
            if (courierMaxDeliveries == slotsLeft) return;

            if (orderBatch.size() == maxCapacity) {
                couriersGroupsOrdersList.add(new CouriersGroupsOrdersEntity(courier.getCourierId(), orderBatch));
                orderBatch = new ArrayList<>(maxCapacity);
            }

            orderBatch.add(order.getOrderId());
            slotsLeft -= 1;
            if (!currentRegions.contains(order.getRegion())) {
                currentRegions.add(order.getRegion());
            }
            weightLeft -= order.getWeight();
            assignedOrders.add(orderId);
        }
        couriersGroupsOrdersList.add(new CouriersGroupsOrdersEntity(courier.getCourierId(), orderBatch));
    }


    private int calculateMaxDeliveries()  {
        List<String> workingHours = courier.getWorkingHours();
        int maxSlots = 0;
        int deliveryDuration = 0;
        switch (courier.getCourierType()) {
            case FOOT -> deliveryDuration = 35;
            case BIKE -> deliveryDuration = 20;
            case AUTO -> deliveryDuration = 12;
        }

        for(String shift : workingHours) {

                DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Instant startTime = null;
            Instant endTime = null;
            try {
                startTime = dateFormat.parse(shift.substring(0, 5)).toInstant();
                endTime = dateFormat.parse(shift.substring(6, 11)).toInstant();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            long totalTime = ChronoUnit.MINUTES.between(startTime, endTime);
            while(totalTime >= 0) {
                maxSlots++;
                totalTime -= deliveryDuration;
            }
        }
        return maxSlots;
    }


}




