package ru.yandex.yandexlavka.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yandex.yandexlavka.exception.NotFoundException;
import ru.yandex.yandexlavka.model.response.BadRequestResponse;
import ru.yandex.yandexlavka.model.response.NotFoundResponse;

import java.util.logging.Logger;

@ControllerAdvice
public class AppExceptionHandler {

    Logger LOG = Logger.getLogger(AppExceptionHandler.class.getName());

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BadRequestResponse> handleException(Exception exception) {

        LOG.info(exception.getMessage());
        BadRequestResponse badRequestResponse = new BadRequestResponse();
        return new ResponseEntity<>(badRequestResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<NotFoundResponse> handleNotFoundException(NotFoundException exception) {

        LOG.info(exception.getMessage());
        NotFoundResponse badRequestResponse = new NotFoundResponse();
        return new ResponseEntity<>(badRequestResponse, HttpStatus.NOT_FOUND);
    }
}
