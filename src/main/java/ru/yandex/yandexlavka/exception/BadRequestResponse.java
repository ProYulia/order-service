package ru.yandex.yandexlavka.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestResponse {

    private final HttpStatus status;

    public BadRequestResponse(HttpStatus status) {
        this.status = status;
    }
}
