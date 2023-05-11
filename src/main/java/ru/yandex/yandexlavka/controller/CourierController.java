package ru.yandex.yandexlavka.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.dto.CourierDto;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.request.CreateCourierRequest;
import ru.yandex.yandexlavka.model.response.CreateCourierResponse;
import ru.yandex.yandexlavka.model.response.GetCourierMetaInfoResponse;
import ru.yandex.yandexlavka.model.response.GetCouriersResponse;
import ru.yandex.yandexlavka.model.response.OrderAssignResponse;
import ru.yandex.yandexlavka.service.CourierService;

import java.util.List;

import static ru.yandex.yandexlavka.controller.ConstantList.*;

@RestController
@RequiredArgsConstructor
public class CourierController {

    private final CourierService courierService;

    @GetMapping(COURIER_BASE_PATH)
    @RateLimiter(name = "rateLimiterApi")
    public GetCouriersResponse getAllCouriers(@RequestParam(name = "offset", required = false) Integer offset,
                                              @RequestParam(name = "limit", required = false) Integer limit) {

        return courierService.getAllCouriers(offset, limit);
    }

    @GetMapping(SINGLE_COURIER_PATH)
    @RateLimiter(name = "rateLimiterApi")
    public CourierDto getCourierByID(@PathVariable int courierId) {

        return courierService.getCourierByID(courierId);
    }

    @PostMapping(COURIER_BASE_PATH)
    @RateLimiter(name = "rateLimiterApi")
    public CreateCourierResponse addCouriers(@Valid @RequestBody CreateCourierRequest createCourier) {

        List<CreateCourierDto> courierDtoList = createCourier.getCreateCourierDtoList();
        return courierService.saveCouriers(courierDtoList);
    }

    @GetMapping(COURIER_META_INFO_PATH)
    @RateLimiter(name = "rateLimiterApi")
    public GetCourierMetaInfoResponse getMetaInfo(@PathVariable int courierId,
                                                  @RequestParam(name = "startDate") String startDate,
                                                  @RequestParam(name = "endDate") String endDate) {

        return courierService.getMetaInfo(courierId, startDate, endDate);
    }

    @GetMapping(COURIER_ASSIGNMENTS_PATH)
    @RateLimiter(name = "rateLimiterApi")
    public OrderAssignResponse getCouriersAssignments(@RequestParam(name = "date") String date,
                                                      @RequestParam(name = "courier_id") Integer courierId) {

        return courierService.getAssignments(date, courierId);
    }

}
