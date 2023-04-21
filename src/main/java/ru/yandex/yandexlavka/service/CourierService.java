package ru.yandex.yandexlavka.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.yandexlavka.model.Courier;
import ru.yandex.yandexlavka.repository.CourierRepository;

import java.util.List;
import java.util.Optional;

//добавить конструктор
@Transactional //todo
@Service
public class CourierService {

    private CourierRepository courierRepository;

    @Autowired// необязательно?
    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public List<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    public Courier getCourierByID(int courierID) {
        Optional<Courier> courier = courierRepository.findById(courierID);
        return courier.orElse(null);
    }
}
