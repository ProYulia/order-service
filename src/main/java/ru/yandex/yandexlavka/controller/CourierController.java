package ru.yandex.yandexlavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.dto.CourierDto;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.request.CreateCourierRequest;
import ru.yandex.yandexlavka.model.response.CreateCourierResponse;
import ru.yandex.yandexlavka.model.response.GetCouriersResponse;
import ru.yandex.yandexlavka.service.CourierService;

import java.util.List;

@RestController
@RequestMapping("/couriers")
public class CourierController {

    @Autowired
    private CourierService courierService;


    @GetMapping
    public GetCouriersResponse getAllCouriers(@RequestParam(name = "offset", required = false) Integer offset,
                                              @RequestParam(name = "limit", required = false) Integer limit) {

        return courierService.getAllCouriers(offset, limit);
    }

    @GetMapping("/{courierID}")
    public CourierDto getCourierByID(@PathVariable int courierID) {
        return courierService.getCourierByID(courierID);
    }

    @PostMapping()
    public CreateCourierResponse addCouriers(@RequestBody CreateCourierRequest createCourier) {

        List<CreateCourierDto> courierDtoList = createCourier.getCreateCourierDtoList();
        return courierService.saveCouriers(courierDtoList);
    }


}
