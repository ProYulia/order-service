package ru.yandex.yandexlavka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.model.Courier;
import ru.yandex.yandexlavka.service.CourierService;

import java.util.List;

@RestController
@RequestMapping("/couriers")
public class CourierController {


    private CourierService courierService;

    @Autowired
    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping()
    public List<Courier> getAllCouriers() { //offset + limit
        return courierService.getAllCouriers();
    }

    @GetMapping("/{courierID}")
    public Courier getCourierByID(@PathVariable int courierID) {
        return courierService.getCourierByID(courierID);
    }

    @PostMapping()
    public String addCouriers(@RequestBody Courier.CourierType courierType,  //TODO
                               List<Integer> regions, List<String> workingHours) {
        return "";
    }

//    @GetMapping("/assignments") // optional
//    public String getCouriersAssignments() {return "";}
//
//    @GetMapping("/meta-info/{courierID}") //optional
//    public String getCourierMetaInfo(@PathVariable int courierID, @RequestBody String startDate, String endDate) {
//        return "";
//    }

}
