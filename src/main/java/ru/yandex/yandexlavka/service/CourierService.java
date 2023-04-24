package ru.yandex.yandexlavka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.mapper.CourierMapper;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;
import ru.yandex.yandexlavka.repository.CourierRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourierService {


    @Autowired
    private CourierMapper courierMapper;

    @Autowired
    private CourierRepository courierRepository;


    public List<CreateCourierDto> getAllCouriers() {
        return courierRepository
                .findAll()
                .stream()
                .map(courierEntity -> courierMapper.courierToDto(courierEntity))
                .collect(Collectors.toList());
    }

    public CreateCourierDto getCourierByID(int courierID) {
        CourierEntity courierEntity = courierRepository.findById(courierID).orElseThrow();
        return courierMapper.courierToDto(courierEntity);
    }

    public void saveCourier(CreateCourierDto createCourierDto) {
        CourierEntity courierResult = courierMapper.courierDtoToEntity(createCourierDto);
        courierRepository.save(courierResult);
    }
}
