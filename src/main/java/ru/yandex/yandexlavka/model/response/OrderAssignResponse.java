package ru.yandex.yandexlavka.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(value = "date")
    private String date;

    @JsonProperty(value = "couriers")
    private List<CouriersGroupsOrders> couriers;

}
