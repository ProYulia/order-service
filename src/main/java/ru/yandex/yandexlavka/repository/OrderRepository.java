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

//    @Modifying
//    @Query(value = "UPDATE orders SET complete_time =:complete_time WHERE order_id =:order_id AND courier_id=:courier_id ",
//            nativeQuery = true)
//    int updateByOrderId(@Param("order_id") Integer order_id,
//                         @Param("courier_id") Integer courier_id,
//                         @Param("complete_time") Instant complete_time);

    @Modifying
    @Query(value = "UPDATE orders o SET complete_time =:complete_time, status = 'COMPLETED' " +
            "FROM order_group og JOIN courier_group cg ON og.group_id=cg.group_id  " +
            "WHERE o.order_id =:order_id AND cg.courier_id=:courier_id ",
            nativeQuery = true)
    int updateByOrderId(@Param("order_id") Integer order_id,
                        @Param("courier_id") Integer courier_id,
                        @Param("complete_time") Instant complete_time);


    @Modifying
    @Query(value = "UPDATE orders SET status=:order_status FROM order_group og WHERE orders.order_id=og.order_id", nativeQuery = true)
    void updateOrderStatus(@Param("order_status") String order_status);


    @Query(value =
            "SELECT SUM(cost) FROM orders o " +
                    "JOIN order_group og ON o.order_id=og.order_id " +
                    "JOIN  courier_group cg ON cg.group_id=og.group_id " +
                    "WHERE cg.courier_id=:courier_id AND (o.complete_time >=:startTime AND o.complete_time <:endTime)",
            nativeQuery = true)
    Integer getTotalOrderCost(@Param("courier_id") Integer courier_id,
                          @Param("startTime") Instant startTime,
                          @Param("endTime") Instant endTime);


    @Query(value = "SELECT COUNT(o.order_id) FROM orders o " +
            "JOIN order_group og ON o.order_id=og.order_id " +
            "JOIN courier_group cg ON cg.group_id=og.group_id " +
            "WHERE cg.courier_id=:courier_id AND (o.complete_time >=:startTime AND o.complete_time <:endTime)",
            nativeQuery = true)
    Integer getTotalOrderCount(@Param("courier_id") Integer courier_id,
                           @Param("startTime") Instant startTime,
                           @Param("endTime") Instant endTime);



    @Query(value =
    "select distinct orders.order_id, t1.courier_id\n" +
            "from orders\n" +
            "    join delivery_hours dh on orders.order_id = dh.order_id\n" +
            "    join regions on orders.region = regions.regions\n" +
            "    join (select *\n" +
            "      from (select regexp_substr(working_hours.working_hours, '[^-]+', 1, 1) as courier_start_time,\n" +
            "                   regexp_substr(working_hours.working_hours, '[^-]+', 1, 2) as courier_end_time,\n" +
            "                   courier_id\n" +
            "            from working_hours) couriers\n" +
            "               join (select regexp_substr(delivery_hours.delivery_hours, '[^-]+', 1, 1) as order_start_time,\n" +
            "                            regexp_substr(delivery_hours.delivery_hours, '[^-]+', 1, 2) as order_end_time,\n" +
            "                            order_id\n" +
            "                     from delivery_hours) orders\n" +
            "                    on courier_start_time < order_end_time and courier_end_time > order_start_time) t1\n" +
            "on orders.order_id = t1.order_id\n" +
            "join courier on t1.courier_id = courier.courier_id\n" +
            "where (courier_type = 'FOOT' and weight <= 10" +
            " or courier_type = 'BIKE' and weight <= 20 " +
            " or courier_type = 'AUTO' and weight <= 40)" +
            "AND orders.status = 'CREATED';",
            nativeQuery = true)
    List<Integer[]> getPotentialAssignments();



}
