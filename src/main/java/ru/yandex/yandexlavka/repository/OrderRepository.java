package ru.yandex.yandexlavka.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.yandex.yandexlavka.model.entity.OrderEntity;

import java.time.Instant;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    @Query(value = "SELECT * from orders limit :limit offset :offset", nativeQuery = true)
    List<OrderEntity> findAll(Integer offset, Integer limit);

    OrderEntity findByOrderId(Integer order_id);

    @Modifying
    @Query(value = "UPDATE orders SET complete_time =:complete_time WHERE order_id =:order_id AND courier_id=:courier_id ",
            nativeQuery = true)
    int updateByOrderId(@Param("order_id") Integer order_id,
                         @Param("courier_id") Integer courier_id,
                         @Param("complete_time") Instant complete_time);


    @Modifying
    @Query(value = "UPDATE orders SET courier_id=:courier_id WHERE order_id=:order_id", nativeQuery = true)
    void updateOrderEntityByOrderId(@Param("order_id") Integer order_id,
                                    @Param("courier_id") Integer courier_id);


    @Query(value =
            "SELECT SUM(cost) FROM orders " +
                    "WHERE courier_id=:courier_id AND (complete_time >=:startTime AND complete_time <:endTime)",
            nativeQuery = true)
    int getTotalOrderCost(@Param("courier_id") Integer courier_id,
                          @Param("startTime") Instant startTime,
                          @Param("endTime") Instant endTime);


    @Query(value = "SELECT COUNT(order_id) FROM orders " +
            "WHERE courier_id=:courier_id AND (complete_time >=:startTime AND complete_time <:endTime)",
            nativeQuery = true)
    int getTotalOrderCount(@Param("courier_id") Integer courier_id,
                           @Param("startTime") Instant startTime,
                           @Param("endTime") Instant endTime);
}
