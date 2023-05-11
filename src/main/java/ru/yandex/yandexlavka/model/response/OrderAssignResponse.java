package ru.yandex.yandexlavka.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.model.dto.CouriersGroupsOrders;

import java.util.List;

@Getter
@Setter
@ToString
@Component
public class OrderAssignResponse {

    private String date;
    private List<CouriersGroupsOrders> couriersGroupsOrdersList;

}
