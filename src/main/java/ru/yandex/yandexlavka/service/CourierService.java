package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.model.dto.CourierDto;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.model.response.CreateCourierResponse;
import ru.yandex.yandexlavka.model.response.GetCourierMetaInfoResponse;
import ru.yandex.yandexlavka.model.response.GetCouriersResponse;
import ru.yandex.yandexlavka.model.response.OrderAssignResponse;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public interface CourierService {

    GetCouriersResponse getAllCouriers(Integer offset, Integer limit);

    CourierDto getCourierByID(int courierID);

    CreateCourierResponse saveCouriers(List<CreateCourierDto> createCourierDto);

    GetCourierMetaInfoResponse getMetaInfo(Integer courierId, String startDate, String endDate);

    OrderAssignResponse getAssignments(String date, Integer courierId);
}
