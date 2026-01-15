package com.github.alxsshv.shortlinkcreationservice.adapter.api.spring.webmvc;

import com.github.alxsshv.shortlinkcreationservice.adapter.api.spring.webmvc.dto.ShortLinkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/** Контроллер, для обработки запросов на создание короткой ссылки от внешних систем по Rest API */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/links")
public class LinkControllerV1 {

    /** Адаптер для взаимодействия Web-MVC контроллера с бизнес-логикой приложения */
    private final LinkWebMvcAdapter linkWebMvcAdapter;

    /** Метод для обработки POST-запроса на создание короткой ссылки */
    @PostMapping()
    public ResponseEntity<String> generateShortLink(@RequestBody ShortLinkRequest request) {
        String shotLink = linkWebMvcAdapter.generateShortLink(request);
        return ResponseEntity.ok(shotLink);
    }

}
