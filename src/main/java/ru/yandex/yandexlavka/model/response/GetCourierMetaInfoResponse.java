package ru.yandex.yandexlavka.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@ToString
@Component
public class GetCourierMetaInfoResponse {

    Integer courierId;
    String type;
    List<Integer> regions;
    List<String> workingHours;
    Integer rating;
    Integer earnings;
}
