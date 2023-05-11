package ru.yandex.yandexlavka.exception;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<BadRequestResponse> handleException(Exception exception) {

        log.error(exception.getMessage(), exception);
        BadRequestResponse badRequestResponse = new BadRequestResponse(HttpStatus.BAD_REQUEST);
        return new  ResponseEntity<>(badRequestResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<NotFoundResponse> handleNotFoundException(NoSuchElementException exception) {

        log.error(exception.getMessage(), exception);
        NotFoundResponse notFoundResponse = new NotFoundResponse(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(notFoundResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ RequestNotPermitted.class })
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public void handleRequestNotPermitted() {
    }

}
