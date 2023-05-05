package ru.yandex.yandexlavka.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/couriers")
public class CourierController {

    @Autowired
    private CourierService courierService;


    @GetMapping
    @RateLimiter(name = "rateLimiterApi")
    public GetCouriersResponse getAllCouriers(@RequestParam(name = "offset", required = false) Integer offset,
                                              @RequestParam(name = "limit", required = false) Integer limit) {

        return courierService.getAllCouriers(offset, limit);
    }

    @GetMapping("/{courierID}")
    @RateLimiter(name = "rateLimiterApi")
    public CourierDto getCourierByID(@PathVariable int courierID) {
        return courierService.getCourierByID(courierID);
    }

    @PostMapping()
    @RateLimiter(name = "rateLimiterApi")
    public CreateCourierResponse addCouriers(@Valid @RequestBody CreateCourierRequest createCourier) {

        List<CreateCourierDto> courierDtoList = createCourier.getCreateCourierDtoList();
        return courierService.saveCouriers(courierDtoList);
    }

    @GetMapping("/meta-info/{courierID}")
    @RateLimiter(name = "rateLimiterApi")
    public GetCourierMetaInfoResponse getMetaInfo(@PathVariable int courierId,
                                                  @RequestParam(name = "startDate") String startDate,
                                                  @RequestParam(name = "endDate") String endDate) {

        return courierService.getMetaInfo(courierId, startDate, endDate);
    }

//    @GetMapping("/assignments")
//    @RateLimiter(name = "rateLimiterApi")
//    public OrderAssignResponse getCouriersAssignments() {
//        return courierService.getAssignments();
//    }

}
