package ru.yandex.yandexlavka.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.*;
import ru.yandex.yandexlavka.model.dto.CreateCourierDto;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourierRequest {

    @Valid
    @JsonProperty("couriers")
    List<CreateCourierDto> createCourierDtoList;

}
