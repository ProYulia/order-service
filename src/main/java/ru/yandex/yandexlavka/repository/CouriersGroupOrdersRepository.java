package ru.yandex.yandexlavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.yandexlavka.model.entity.CouriersGroupsOrdersEntity;

import java.util.List;

@Repository
public interface CouriersGroupOrdersRepository extends JpaRepository<CouriersGroupsOrdersEntity, Integer> {

    List<CouriersGroupsOrdersEntity> findAllByCourierIdAndAndDate(Integer courierId, String date);

    List<CouriersGroupsOrdersEntity> findAllByDate(String date);

}
