package com.github.alxsshv.original_links_getting_service.adapter.api.spring.webmvc;

import com.github.alxsshv.original_links_getting_service.adapter.api.spring.webmvc.dto.ServiceMessage;
import com.github.alxsshv.original_links_getting_service.adapter.api.spring.webmvc.exception.IllegalShortLinkTokenException;
import com.github.alxsshv.original_links_getting_service.application.domain.exception.LinkNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Обработчик исключений */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** Перехватывает исключения {@link LinkNotFoundException} и возвращает в качестве ответа на запрос
     * ResponseEntity с сообщением об ошибке в виде объекта {@link ServiceMessage} */
    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<ServiceMessage> handleLinkNotFoundException(LinkNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ServiceMessage(e.getMessage()));
    }

    /** Перехватывает исключения {@link IllegalShortLinkTokenException} и возвращает в качестве ответа на запрос
     * ResponseEntity с сообщением об ошибке в виде объекта {@link ServiceMessage} */
    @ExceptionHandler(IllegalShortLinkTokenException.class)
    public ResponseEntity<ServiceMessage> handleIllegalArgumentException(IllegalShortLinkTokenException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ServiceMessage(e.getMessage()));
    }

}
