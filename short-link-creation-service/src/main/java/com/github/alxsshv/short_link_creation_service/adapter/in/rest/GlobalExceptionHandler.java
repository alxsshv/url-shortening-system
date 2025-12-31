package com.github.alxsshv.short_link_creation_service.adapter.in.rest;

import com.github.alxsshv.short_link_creation_service.adapter.in.rest.dto.ServiceMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ServiceMessage message = new ServiceMessage("Invalid value for field" + Arrays.stream(ex.getDetailMessageArguments())
                .map(Object::toString).collect(Collectors.joining(" ")));
        return ResponseEntity.badRequest().body(message);
    }

}
