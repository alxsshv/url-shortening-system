package com.github.alxsshv.url_service_api.adapter.api.spring.webmvc;

import com.github.alxsshv.url_service_api.adapter.api.spring.webmvc.dto.ShortLinkRequest;
import com.github.alxsshv.url_service_api.application.api.GetOriginalUrlApi;
import com.github.alxsshv.url_service_api.application.api.GetShortLinkApi;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

/** Контроллер, для обработки запросов на создание короткой ссылки по Rest API */
@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
@Slf4j
public class LinkControllerV1 {

    /** Адаптер для взаимодействия с бизнес-логикой приложения в части получения оригинальной ссылки */
    private final GetOriginalUrlApi originalUrlApi;

    /** Адаптер для взаимодействия с бизнес-логикой приложения в части создания короткой ссылки */
    private final GetShortLinkApi shortLinkApi;

    /** Свойство, опредляющее адрес, по которому будет доступен сервис коротких ссылок (хост, порт, базовый url) */
    @Value("${app.short-link-address-template}")
    private String shortLinkUrlAddress;

    /** Метод, обрабатывающий запрос на создание короткой ссылки.
     * @param request - DTO для передачи оригинальной ссылки в виде объекта {@link ShortLinkRequest}.
     * @return возвращает короткую ссылку, включая хост, порт, базовый URL и уникальный токен. */
    @PostMapping("/link")
    public ResponseEntity<String> createShortLink(@RequestBody @Valid ShortLinkRequest request) {
        log.info("Request for creating short link");
        String url = shortLinkUrlAddress + shortLinkApi.getShortLink(request.url(), request.ttl());
        log.info("Short link created: {}", url);
        return ResponseEntity.ok(url);
    }

    /** Метод, обрабатывающий запрос на получение оригинальной ссылки по короткой ссылке.
     * @param shortLink - короткая ссылка (уникальная часть). Длина токена короткой ссылки - 6 символов.
     * @return возвращает {@link ResponseEntity} с оригинальной ссылкой
     * */
    @GetMapping("/link/{shortLink}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable
                                                     @Size(min = 6, max = 6, message = "invalid short link size") String shortLink) {
        log.info("Getting original url for short link: {}", shortLink);
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .body(originalUrlApi.getOriginalUrl(shortLink));
    }

    /** Метод, обрабатывающий запрос на перенаправление по короткой ссылке на оригинальный url.
     * @param shortLink - короткая ссылка (уникальная часть). Длина токена короткой ссылки - 6 символов.
     * @return возвращает {@link RedirectView}, с оригинальной ссылкой для перенаправления*/
    @GetMapping("/{shortLink}")
    public RedirectView redirectOriginalUrl(@PathVariable
                                                @Size(min = 6, max = 6, message = "invalid short link size") String shortLink) {
        log.info("Redirect to original url by short link: {}", shortLink);
        return new RedirectView(originalUrlApi.getOriginalUrl(shortLink));
    }


}
