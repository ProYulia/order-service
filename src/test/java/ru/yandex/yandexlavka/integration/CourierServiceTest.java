package ru.yandex.yandexlavka.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.service.CourierService;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class CourierServiceTest {

    @Autowired
    private CourierService courierService;


    @Test
    public void shouldGetAllCouriers() {
        List<CreateCourierDto> allCouriers = courierService.getAllCouriers();
        Assertions.assertEquals(Collections.EMPTY_LIST, allCouriers);
    }

    @Test
    public void shouldGetCourierById() {
        CreateCourierDto courierDto = courierService.getCourierByID(1);
        Assertions.assertNull(courierDto);
    }

    @Test
    public void shouldCreateCouriers(CreateCourierDto[] createCourierDto) {
        List<CourierEntity> courierEntities = courierService.saveCouriers(createCourierDto);
        Assertions.assertEquals(Collections.EMPTY_LIST, courierEntities);
    }


}
