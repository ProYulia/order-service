package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.model.response.OrderAssignResponse;

public interface AssignOrderService {

    OrderAssignResponse assignOrdersToCouriers(String date);

    OrderAssignResponse getCourierAssignments(String date, Integer courierId);
}
