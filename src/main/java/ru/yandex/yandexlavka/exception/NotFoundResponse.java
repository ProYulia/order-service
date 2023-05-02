package ru.yandex.yandexlavka.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public record NotFoundResponse(HttpStatus status) {

}
