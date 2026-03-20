package com.github.alxsshv.shortlinkcreationservice.adapter.api.spring.webmvc;

import com.github.alxsshv.shortlinkcreationservice.adapter.api.spring.webmvc.dto.ServiceMessage;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

/** Обработчик исключений */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /** Перехватывает исключения {@link MethodArgumentNotValidException} и возвращает в качестве ответа на запрос
     * ResponseEntity с сообщением об ошибке в виде объекта {@link ServiceMessage} */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ServiceMessage message = new ServiceMessage("Invalid value for field" + Arrays.stream(ex.getDetailMessageArguments())
                .map(Object::toString).collect(Collectors.joining(" ")));
        return ResponseEntity.badRequest().body(message);
    }

    /** Перехватывает исключения {@link IllegalArgumentException} и возвращает в качестве ответа на запрос
     * ResponseEntity с сообщением об ошибке в виде объекта {@link ServiceMessage} */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ServiceMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error(ex.getMessage());
        ServiceMessage message = new ServiceMessage("Internal server error, please try again later");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    /** Перехватывает исключения {@link ConstraintViolationException} и возвращает в качестве ответа на запрос
     * ResponseEntity с сообщением об ошибке в виде объекта {@link ServiceMessage} */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ServiceMessage> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex.getMessage());
        ServiceMessage message = new ServiceMessage(ex.getConstraintViolations().stream().findFirst().get().getMessage());
        return ResponseEntity.badRequest().body(message);
    }

}
