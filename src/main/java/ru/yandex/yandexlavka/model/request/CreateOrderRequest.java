package ru.yandex.yandexlavka.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;

import java.util.List;

@Component
@Getter
@Setter
@ToString
public class CreateOrderRequest {

    @JsonProperty(value = "orders")
    List<CreateOrderDto> createOrderDtoList;
}
