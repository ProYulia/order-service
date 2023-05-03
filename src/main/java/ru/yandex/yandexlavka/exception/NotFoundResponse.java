package ru.yandex.yandexlavka.exception;

import org.springframework.http.HttpStatus;


public record NotFoundResponse(HttpStatus status) {

}
