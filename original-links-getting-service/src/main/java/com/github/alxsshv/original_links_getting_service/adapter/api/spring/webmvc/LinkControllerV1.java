package com.github.alxsshv.original_links_getting_service.adapter.api.spring.webmvc;

import com.github.alxsshv.original_links_getting_service.adapter.api.spring.webmvc.dto.Url;
import com.github.alxsshv.original_links_getting_service.adapter.api.spring.webmvc.exception.IllegalShortLinkTokenException;
import com.github.alxsshv.original_links_getting_service.application.api.ForFindOriginalUrlApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Контроллер, для обработки запросов на получение оригинальной ссылки от внешних систем по Rest API */
@RestController
@RequestMapping("/api/v1/links")
@RequiredArgsConstructor
@Validated
@Slf4j
public class LinkControllerV1 {

    /** Реализация интерфейса, описывающего Api приложения для получения запросов от внешних систем */
    private final ForFindOriginalUrlApi forFindOriginalUrlApi;

    /** Метод получения оригинального URL по токену короткой ссылки*/
    @RequestMapping("/{token}")
    public ResponseEntity<Url> getLink(@PathVariable String token) {
        if (!StringUtils.hasText(token)) {
            throw new IllegalShortLinkTokenException("Token has an incorrect format");
        }
        log.info("Received request for token: {}", token);
        return ResponseEntity.ok(new Url(forFindOriginalUrlApi.findOriginalLink(token).getUrl()));
    }

}


