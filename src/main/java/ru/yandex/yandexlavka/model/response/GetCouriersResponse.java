package ru.yandex.yandexlavka.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.model.dto.CourierDto;

import java.util.List;

@Getter
@Setter
@ToString
@Component
public class GetCouriersResponse {

    private List<CourierDto> couriers;
    private Integer offset;
    private Integer limit;

}
