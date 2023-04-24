package ru.yandex.yandexlavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.service.CourierService;

import java.util.List;

@RestController
@RequestMapping("/couriers")
public class CourierController {

    @Autowired
    private CourierService courierService;

    @GetMapping()
    public List<CourierEntity> getAllCouriers() { //offset + limit
        return courierService.getAllCouriers();
    }

    @GetMapping("/{courierID}")
    public CreateCourierDto getCourierByID(@PathVariable int courierID) {
        return courierService.getCourierByID(courierID);
    }

    @PostMapping()
    public CourierEntity addCouriers(@RequestBody CreateCourierDto createCourierDto) {
        return courierService.saveCourier(createCourierDto);
    }

//    @GetMapping("/assignments") // optional
//    public String getCouriersAssignments() {return "";}
//
//    @GetMapping("/meta-info/{courierID}") //optional
//    public String getCourierMetaInfo(@PathVariable int courierID, @RequestBody String startDate, String endDate) {
//        return "";
//    }

}
