//package ru.yandex.yandexlavka.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import ru.yandex.yandexlavka.mapper.OrderMapper;
//import ru.yandex.yandexlavka.model.dto.CouriersGroupsOrders;
//import ru.yandex.yandexlavka.model.GroupOrders;
//import ru.yandex.yandexlavka.model.entity.CourierEntity;
//import ru.yandex.yandexlavka.model.entity.OrderEntity;
//import ru.yandex.yandexlavka.model.response.OrderAssignResponse;
//import ru.yandex.yandexlavka.repository.CourierRepository;
//import ru.yandex.yandexlavka.repository.OrderRepository;
//import ru.yandex.yandexlavka.service.AssignOrderService;
//
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class AssignOrderServiceImpl implements AssignOrderService {
//
//    @Autowired
//    private CourierServiceImpl courierServiceImpl;
//
//    @Autowired
//    private CourierRepository courierRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    private final Map<Integer, List<Integer>> possibleAssignments = new HashMap<>();
//
//    private final List<Integer> assignedOrders = new ArrayList<>();
//    private int courierMaxDeliveries;
//
//    private GroupOrders groupOrders;
//    private CouriersGroupsOrders couriersGroupsOrders;
//
//
//    private CourierEntity courier;
//    private OrderEntity order;
//    private final int FOOT_MAX_WEIGHT = 10;
//    private final int FOOT_MAX_CAPACITY = 2;
//    private final int FOOT_MAX_REGIONS = 1;
//    private final int BIKE_MAX_WEIGHT = 20;
//    private final int  BIKE_MAX_CAPACITY = 4;
//    private final int BIKE_MAX_REGIONS = 2;
//
//    private final int AUTO_MAX_WEIGHT = 40;
//    private final int AUTO_MAX_CAPACITY = 7;
//    private final int AUTO_MAX_REGIONS = 3;
//
//
//    public OrderAssignResponse assignOrdersToCouriers(String date) {
//        getPotentialAssignments();
//        for(Integer courierId : possibleAssignments.keySet()) {
//            courier = courierRepository.findByCourierId(courierId);
//            courierMaxDeliveries = calculateMaxDeliveries();
//            switch (courier.getCourierType()) {
//                case FOOT -> createBatchForFoot(possibleAssignments.get(courierId));
//                case BIKE -> createBatchForBike(possibleAssignments.get(courierId));
//                case AUTO -> createBatchForAuto(possibleAssignments.get(courierId));
//            }
//        }
//
//
//    }
//
//    private void getPotentialAssignments() {
//        List<Integer[]> courierToOrder = orderRepository.getPotentialAssignments();
//        for (Integer[] item : courierToOrder) {
//            Integer orderId = item[0];
//            Integer courierId = item[1];
//            possibleAssignments.computeIfAbsent(courierId, k -> new ArrayList<Integer>());
//            possibleAssignments.get(courierId).add(orderId);
//        }
//    }
//
//    private void createBatchForFoot(List<Integer> possibleOrders) {
//        int weightLeft = FOOT_MAX_WEIGHT;
//        int slotsLeft = courierMaxDeliveries * FOOT_MAX_CAPACITY;
//        int currentRegion = -1;
//        groupOrders = new GroupOrders(FOOT_MAX_CAPACITY);
//        couriersGroupsOrders = new CouriersGroupsOrders(courier.getCourierId());
//
//        for(Integer orderId : possibleOrders) {
//            order = orderRepository.findByOrderId(orderId);
//            if (assignedOrders.contains(orderId)) continue;
//            if (order.getWeight() > weightLeft) continue;
//            if (currentRegion != -1 && order.getRegion() != currentRegion) continue;
//            if (courierMaxDeliveries == slotsLeft) return;
//            orderRepository.updateOrderEntityByOrderId(orderId, courier.getCourierId());
//
//            if (groupOrders.getBatch().size() == FOOT_MAX_CAPACITY) {
//                couriersGroupsOrders.getCourierBatch().add(groupOrders);
//                groupOrders = new GroupOrders(FOOT_MAX_CAPACITY);
//            }
//            groupOrders.getBatch().add(orderMapper.orderEntityToDto(order));
//            slotsLeft -= 1;
//            currentRegion = order.getRegion();
//            weightLeft -= order.getWeight();
//            assignedOrders.add(orderId);
//        }
//    }
//
//    private void createBatchForBike(List<Integer> possibleOrders) {
//        int weightLeft = BIKE_MAX_WEIGHT;
//        int slotsLeft = courierMaxDeliveries * BIKE_MAX_CAPACITY;
//        List<Integer> currentRegions = new ArrayList<>();
//        groupOrders = new GroupOrders(BIKE_MAX_CAPACITY);
//        couriersGroupsOrders = new CouriersGroupsOrders(courier.getCourierId());
//
//        for(Integer orderId : possibleOrders) {
//            order = orderRepository.findByOrderId(orderId);
//            if (assignedOrders.contains(orderId)) continue;
//            if (order.getWeight() > weightLeft) continue;
//            if (currentRegions.size() == BIKE_MAX_REGIONS && !currentRegions.contains(order.getRegion())) continue;
//            if (courierMaxDeliveries == slotsLeft) return;
//            orderRepository.updateOrderEntityByOrderId(orderId, courier.getCourierId());
//
//            if (groupOrders.getBatch().size() == BIKE_MAX_CAPACITY) {
//                couriersGroupsOrders.getCourierBatch().add(groupOrders);
//                groupOrders = new GroupOrders(BIKE_MAX_CAPACITY);
//            }
//            groupOrders.getBatch().add(orderMapper.orderEntityToDto(order));
//
//            slotsLeft -= 1;
//            if (!currentRegions.contains(order.getRegion())) {
//                currentRegions.add(order.getRegion());
//            }
//            weightLeft -= order.getWeight();
//            assignedOrders.add(orderId);
//        }
//    }
//
//    private void createBatchForAuto(List<Integer> possibleOrders) {
//        int weightLeft = AUTO_MAX_WEIGHT;
//        int slotsLeft = courierMaxDeliveries * AUTO_MAX_CAPACITY;
//        List<Integer> currentRegions = new ArrayList<>();
//
//        for(Integer orderId : possibleOrders) {
//            order = orderRepository.findByOrderId(orderId);
//            if (assignedOrders.contains(orderId)) continue;
//            if (order.getWeight() > weightLeft) continue;
//            if (currentRegions.size() == AUTO_MAX_REGIONS && !currentRegions.contains(order.getRegion())) continue;
//            if (courierMaxDeliveries == slotsLeft) return;
//            orderRepository.updateOrderEntityByOrderId(orderId, courier.getCourierId());
//
//            if (groupOrders.getBatch().size() == AUTO_MAX_CAPACITY) {
//                couriersGroupsOrders.getCourierBatch().add(groupOrders);
//                groupOrders = new GroupOrders(AUTO_MAX_CAPACITY);
//            }
//            groupOrders.getBatch().add(orderMapper.orderEntityToDto(order));
//
//            slotsLeft -= 1;
//            if (!currentRegions.contains(order.getRegion())) {
//                currentRegions.add(order.getRegion());
//            }
//            weightLeft -= order.getWeight();
//            assignedOrders.add(orderId);
//        }
//
//    }
//
//    private int calculateMaxDeliveries() {
//        List<String> workingHours = courier.getWorkingHours();
//        int maxSlots = 0;
//        int deliveryDuration = 0;
//        switch (courier.getCourierType()) {
//            case FOOT -> deliveryDuration = 35;
//            case BIKE -> deliveryDuration = 20;
//            case AUTO -> deliveryDuration = 12;
//        }
//
//        for(String shift : workingHours) {
//            Instant startTime = Instant.parse(shift.substring(0, 6));
//            Instant endTime = Instant.parse(shift.substring(6, 11));
//            long totalTime = ChronoUnit.MINUTES.between(startTime, endTime);
//            while(totalTime >= 0) {
//                maxSlots++;
//                totalTime -= deliveryDuration;
//            }
//        }
//        return maxSlots;
//    }
//
//
//}




