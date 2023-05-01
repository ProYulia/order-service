package ru.yandex.yandexlavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yandex.yandexlavka.model.dto.CourierDto;
import ru.yandex.yandexlavka.model.entity.CourierEntity;

import java.util.List;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Integer> {
    @Query(value = "SELECT * from courier limit :limit offset :offset", nativeQuery = true)
    List<CourierEntity> findAll(Integer offset, Integer limit);


}
