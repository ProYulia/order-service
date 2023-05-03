package ru.yandex.yandexlavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.yandex.yandexlavka.model.entity.OrderEntity;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query(value = "SELECT * from orders limit :limit offset :offset", nativeQuery = true)
    List<OrderEntity> findAll(Integer offset, Integer limit);

    @Modifying
    @Query(value = "UPDATE orders o SET o.complete_time =:complete_time WHERE o.order_id =:order_id AND o.courier_id=:courier_id ",
            nativeQuery = true)
    OrderEntity updateByOrderId(@Param("order_id") Integer order_id,
                                @Param("courier_id") Integer courier_id,
                                @Param("complete_time") String complete_time);
}
