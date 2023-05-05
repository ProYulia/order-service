package ru.yandex.yandexlavka.service;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourierService {


    @Autowired
    private CourierRepository courierRepository;

    @Autowired
    private CourierMapper courierMapper;

    @Autowired
    private CreateCourierResponse createCourierResponse;

    @Autowired
    private GetCouriersResponse getCouriersResponse;

    @Autowired
    private GetCourierMetaInfoResponse getCourierMetaInfoResponse;

    @Autowired
    private OrderRepository orderRepository;


    public GetCouriersResponse getAllCouriers(Integer offset, Integer limit) {

//        int pageNumber = offset/limit;
//        Pageable pageable = PageRequest.of(pageNumber, limit);

        if (offset == null) offset = 0;
        else if (offset < 0) throw new IllegalArgumentException();

        if (limit == null) limit = 1;
        else if (limit < 0) throw new IllegalArgumentException();

        getCouriersResponse.setOffset(offset);
        getCouriersResponse.setLimit(limit);

        List<CourierDto> courierDtoList = courierRepository
                .findAll(offset, limit)
                .stream()
                .map(entity -> courierMapper.entityToCreateResponse(entity))
                .toList();

        getCouriersResponse.setCouriers(courierDtoList);

        return getCouriersResponse;
    }

    public CourierDto getCourierByID(int courierID) {
        CourierEntity courierEntity = courierRepository.findById(courierID).orElseThrow();
        return courierMapper.entityToCreateResponse(courierEntity);
    }

    public CreateCourierResponse saveCouriers(List<CreateCourierDto> createCourierDto) {

        List<CourierEntity> courierEntityList = new ArrayList<>();

        for (CreateCourierDto courierDto : createCourierDto) {
            CourierEntity courierEntity = courierMapper.courierDtoToEntity(courierDto);
            courierEntityList.add(courierEntity);
        }

        courierRepository.saveAll(courierEntityList);

        List<CourierDto> courierDtoList = courierEntityList
                .stream()
                .map(entity -> courierMapper.entityToCreateResponse(entity))
                .toList();
        createCourierResponse.setCouriers(courierDtoList);

        return createCourierResponse;
    }

    public GetCourierMetaInfoResponse getMetaInfo(Integer courierId, String startDate, String endDate) {

        Instant start = Instant.parse(startDate);
        Instant end = Instant.parse(endDate);

        CourierEntity entity = courierRepository.findByCourierId(courierId);
        String courierType = entity.getCourierType().name();
        int totalOrderCost = orderRepository.getTotalOrderCost(courierId, start, end);
        int totalOrderCount = orderRepository.getTotalOrderCount(courierId, start, end);

        int multiplier;
        switch (courierType) {
            case "FOOT" -> multiplier = 3;
            case "BIKE" -> multiplier = 2;
            case "AUTO" -> multiplier = 1;
            default -> multiplier = 0;
        }

        int earnings = totalOrderCost * multiplier;
        int rating = (int) ((totalOrderCount / ChronoUnit.HOURS.between(start, end)) * multiplier);

        return courierMapper.entityToMetaInfoResponse(entity, rating, earnings, courierType);
    }
}
