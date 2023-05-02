package ru.yandex.yandexlavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yandex.yandexlavka.model.entity.OrderEntity;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query(value = "SELECT * from orders limit :limit offset :offset", nativeQuery = true)
    List<OrderEntity> findAll(Integer offset, Integer limit);
}
