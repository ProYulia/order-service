package ru.yandex.yandexlavka.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundResponse {

    private final HttpStatus status;

    public NotFoundResponse(HttpStatus status) {
        this.status = status;
    }

}
