package ru.yandex.yandexlavka.controller;

public class ConstantList {

    // paths for OrderController
    public static final String ORDER_BASE_PATH = "/orders";
    public static final String SINGLE_ORDER_PATH = ORDER_BASE_PATH + "/{orderId}";
    public static final String COMPLETE_ORDER_PATH = ORDER_BASE_PATH + "/complete";
    public static final String ASSIGN_ORDERS_PATH = ORDER_BASE_PATH + "/assign";

    // paths for CourierController
    public static final String COURIER_BASE_PATH = "/couriers";
    public static final String SINGLE_COURIER_PATH = COURIER_BASE_PATH + "/{courierId}";
    public static final String COURIER_META_INFO_PATH = COURIER_BASE_PATH + "/meta-info/{courierId}";
    public static final String COURIER_ASSIGNMENTS_PATH = COURIER_BASE_PATH + "/assignments";


}
