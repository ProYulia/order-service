package ru.yandex.yandexlavka.model.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.model.dto.CourierDto;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Component
public class GetCouriersResponse {

    private List<CourierDto> courierDtoList;
    private Integer offset;
    private Integer limit;

    @Override
    public String toString() {
        return "GetCouriersResponse{" +
                "createCourierDtoList=" + courierDtoList.toString() +
                ", offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
