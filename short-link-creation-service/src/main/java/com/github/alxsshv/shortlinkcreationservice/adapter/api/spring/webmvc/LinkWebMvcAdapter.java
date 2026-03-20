package com.github.alxsshv.shortlinkcreationservice.adapter.api.spring.webmvc;


import com.github.alxsshv.shortlinkcreationservice.adapter.api.spring.webmvc.dto.ShortLinkRequest;
import com.github.alxsshv.shortlinkcreationservice.application.api.GenerateShortLinkApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.Optional;

/** Адаптер для взаимодействия Web-MVC контроллера с бизнес-логикой приложения */
@Component
@RequiredArgsConstructor
@Validated
public class LinkWebMvcAdapter {

    /** Реализация интерфейса, описывающего Api приложения для обработки запросов
     * от внешних систем на получение короткой ссылки */
    private final GenerateShortLinkApi generateShortLinkApi;

    /** Метод для получения короткой ссылки.
     * @param request - запрос на получение короткой ссылки в виде DTO {@link ShortLinkRequest}.
     * @return возвращает короткую ссылку в виде строки {@link String}*/
    @Transactional
    public String generateShortLink(@Valid ShortLinkRequest request) {
        Optional<Duration> ttlOpt = Optional.ofNullable(request.ttl()).map(Duration::parse);
        return generateShortLinkApi.generateShortLink(request.url(), ttlOpt);
    }

}
