package ru.yandex.yandexlavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.yandexlavka.model.entity.OrderGroupEntity;

@Repository
public interface CouriersGroupOrdersRepository extends JpaRepository<OrderGroupEntity, Integer> {


}
