package ru.yandex.yandexlavka.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.model.dto.CouriersGroupsOrders;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class OrderAssignResponse {

    private String date;
    private List<CouriersGroupsOrders> couriers;

}
