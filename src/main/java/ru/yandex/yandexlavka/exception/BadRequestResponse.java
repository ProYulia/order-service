package ru.yandex.yandexlavka.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public record BadRequestResponse(HttpStatus status) {

}
