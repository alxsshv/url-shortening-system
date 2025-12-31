package com.github.alxsshv.url_service_api.adapter.api.spring.webmvc;


import com.github.alxsshv.url_service_api.adapter.api.spring.webmvc.dto.ServiceMessage;
import com.github.alxsshv.url_service_api.adapter.spi.forgettingoriginalurl.spring.webmvc.exception.OriginalLinkServiceBadRequestException;
import com.github.alxsshv.url_service_api.adapter.spi.forgettingoriginalurl.spring.webmvc.exception.OriginalUrlNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServiceMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ServiceMessage message = new ServiceMessage("Invalid value for field" + Arrays.stream(ex.getDetailMessageArguments())
                .map(Object::toString).collect(Collectors.joining(" ")));
        log.error(message.message());
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(OriginalUrlNotFoundException.class)
    public ResponseEntity<ServiceMessage> handleUrlNotFoundException(OriginalUrlNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ServiceMessage("Url not found or expired"));
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ServiceMessage> handleResourceAccessException(ResourceAccessException ex) {
        log.error("Service is not responding: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ServiceMessage("Operation is temporarily unavailable"));
    }

    @ExceptionHandler(OriginalLinkServiceBadRequestException.class)
    public ResponseEntity<ServiceMessage> handleOriginalLinkServiceBadRequestException(OriginalLinkServiceBadRequestException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ServiceMessage("Link is not valid"));
    }



}
