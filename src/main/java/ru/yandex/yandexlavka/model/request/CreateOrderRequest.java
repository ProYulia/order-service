package ru.yandex.yandexlavka.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.*;
import ru.yandex.yandexlavka.model.dto.CreateOrderDto;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @Valid
    @JsonProperty("orders")
    List<CreateOrderDto> createOrderDtoList;
}
