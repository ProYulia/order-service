package ru.yandex.yandexlavka.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.repository.CourierRepository;
import ru.yandex.yandexlavka.service.CourierService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class CourierServiceTest {

    @Autowired
    private CourierService courierService;

    @Autowired
    CourierRepository courierRepository;


    @Test
    public void shouldGetAllCouriers() {
        List<CreateCourierDto> allCouriers = courierService.getAllCouriers();
        Assertions.assertEquals(Collections.EMPTY_LIST, allCouriers);
    }

    @Test
    public void shouldGetCourierById() {
        // given
        int id = 1;

        //when
        CreateCourierDto courierDto = courierService.getCourierByID(id);

        //then
        Assertions.assertEquals(CourierEntity.CourierType.FOOT, courierDto.getCourierType());
        Assertions.assertEquals("10:00-18:00", courierDto.getWorkingHours().get(0));
    }

    @Test
    public void shouldCreateCouriers() {
        //given
        List<CreateCourierDto> createCourierDto = Arrays.asList(new CreateCourierDto(CourierEntity.CourierType.FOOT,
                Arrays.asList(0, 1),
                Arrays.asList("10:00-18:00")));

        //when
        List<CourierEntity> courierEntities = courierService.saveCouriers(createCourierDto);
        List<CourierEntity> all = courierRepository.findAll();

        //then
        Assertions.assertTrue(all.stream()
                .anyMatch(courierEntity -> courierEntity.getCourierType().equals(CourierEntity.CourierType.FOOT)));
    }

    @BeforeEach
    public void dropAll() {
       // courierRepository.deleteAll(); //where id > 100
        List<CreateCourierDto> createCourierDto = Arrays.asList(new CreateCourierDto(CourierEntity.CourierType.FOOT,
                Arrays.asList(0, 1),
                Arrays.asList("10:00-18:00")));

        courierRepository.save(new CourierEntity())
    }


}
