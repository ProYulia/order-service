package ru.yandex.yandexlavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.yandexlavka.model.entity.DeliveryHoursEntity;

@Repository
public interface ShiftRepository extends JpaRepository<DeliveryHoursEntity, Integer> {


}
