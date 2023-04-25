package ru.yandex.yandexlavka.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CompletedOrderDto {

    private Integer courierId;
    private Integer orderId;
    private String completeTime;
}
