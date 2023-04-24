package ru.yandex.yandexlavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.yandexlavka.model.entity.CourierEntity;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Integer> {
}
