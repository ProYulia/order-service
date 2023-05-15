package ru.yandex.yandexlavka.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.mapper.CourierMapper;
import ru.yandex.yandexlavka.model.dto.CourierDto;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.model.response.CreateCourierResponse;
import ru.yandex.yandexlavka.model.response.GetCourierMetaInfoResponse;
import ru.yandex.yandexlavka.model.response.GetCouriersResponse;
import ru.yandex.yandexlavka.repository.CourierRepository;
import ru.yandex.yandexlavka.repository.OrderRepository;
import ru.yandex.yandexlavka.service.CourierService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {

    private final CourierRepository courierRepository;
    private final CourierMapper courierMapper;
    private final CreateCourierResponse createCourierResponse;
    private final GetCouriersResponse getCouriersResponse;
    private final OrderRepository orderRepository;


    public GetCouriersResponse getAllCouriers(Integer offset, Integer limit) {

        if (offset == null) offset = 0;
        else if (offset < 0) throw new IllegalArgumentException();

        if (limit == null) limit = 1;
        else if (limit < 0) throw new IllegalArgumentException();

        getCouriersResponse.setOffset(offset);
        getCouriersResponse.setLimit(limit);

        List<CourierDto> courierDtoList = courierRepository
                .findAll(offset, limit)
                .stream()
                .map(courierMapper::entityToCreateResponse)
                .toList();

        getCouriersResponse.setCouriers(courierDtoList);

        return getCouriersResponse;
    }

    public CourierDto getCourierByID(int courierID) {

        if (courierID < 0) throw new IllegalArgumentException();
        CourierEntity courierEntity = courierRepository.findById(courierID).orElseThrow();
        return courierMapper.entityToCreateResponse(courierEntity);
    }

    @Transactional
    public CreateCourierResponse saveCouriers(List<CreateCourierDto> createCourierDto) {

        List<CourierEntity> courierEntityList = new ArrayList<>();

        for (CreateCourierDto courierDto : createCourierDto) {
            CourierEntity courierEntity = courierMapper.courierDtoToEntity(courierDto);
            courierEntityList.add(courierEntity);
        }

        courierRepository.saveAll(courierEntityList);

        List<CourierDto> courierDtoList = courierEntityList
                .stream()
                .map(courierMapper::entityToCreateResponse)
                .toList();
        createCourierResponse.setCouriers(courierDtoList);

        return createCourierResponse;
    }

    public GetCourierMetaInfoResponse getMetaInfo(Integer courierId, String startDate, String endDate) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Instant start;
        Instant end;
        try {
            start = dateFormat.parse(startDate).toInstant();
            end = dateFormat.parse(endDate).toInstant();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        CourierEntity entity = courierRepository.findByCourierId(courierId);
        String courierType = entity.getCourierType().name();
        Integer totalOrderCost = orderRepository.getTotalOrderCost(courierId, start, end);
        Integer totalOrderCount = orderRepository.getTotalOrderCount(courierId, start, end);

        int multiplier;
        switch (courierType) {
            case "FOOT" -> multiplier = 3;
            case "BIKE" -> multiplier = 2;
            case "AUTO" -> multiplier = 1;
            default -> multiplier = 0;
        }

        int earnings;
        Integer rating;

        if (totalOrderCount == null || totalOrderCost == null) {
            rating = null;
            earnings = 0;
        } else {
            earnings = totalOrderCost * multiplier;
            long hoursBetween = ChronoUnit.HOURS.between(start, end);
            rating = (int) ((totalOrderCount / hoursBetween) * multiplier);
        }

        return courierMapper.entityToMetaInfoResponse(entity, rating, earnings, courierType);
    }

}
