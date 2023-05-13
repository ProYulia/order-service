package ru.yandex.yandexlavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.yandexlavka.model.dto.CouriersGroupsOrders;
import ru.yandex.yandexlavka.model.entity.CouriersGroupsOrdersEntity;

@Repository
public interface CouriersGroupOrdersRepository extends JpaRepository<CouriersGroupsOrdersEntity, Integer> {

}
