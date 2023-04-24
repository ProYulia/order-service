package ru.yandex.yandexlavka.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CreateOrderDto {

    private float weight;
    private int region;
    //private List<String> deliveryHours;todo
    private int cost;
    private String completeTime;


}
