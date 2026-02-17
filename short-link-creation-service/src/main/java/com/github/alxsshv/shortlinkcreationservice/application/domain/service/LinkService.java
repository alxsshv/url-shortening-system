package com.github.alxsshv.shortlinkcreationservice.application.domain.service;

import com.github.alxsshv.shortlinkcreationservice.application.acl.GenerateShortLinkTokenAcl;
import com.github.alxsshv.shortlinkcreationservice.application.acl.GetNextIdAcl;
import com.github.alxsshv.shortlinkcreationservice.application.acl.SaveLinkAcl;
import com.github.alxsshv.shortlinkcreationservice.application.api.GenerateShortLinkApi;
import com.github.alxsshv.shortlinkcreationservice.application.domain.model.Link;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/** Сервис для генерации короткой ссылки */
@RequiredArgsConstructor
public class LinkService implements GenerateShortLinkApi {

    /** Буферный слой для получения идентификаторов из базы данных*/
    private final GetNextIdAcl getNextIdAcl;

    /** Буферный слой для сохранения ссылок в БД */
    private final SaveLinkAcl saveLinkAcl;

    /** Буферный слой для получения токенов коротких ссылок из внешней библиотеки */
    private final GenerateShortLinkTokenAcl tokenGenerator;

    /** Метод для генерации короткой ссылки.
     * @param originalUrl оригинальная ссылка,
     * @param ttlOpt  - время жизни ссылки (опционально).
     * @return возвращает короткую ссылку в виде строки-токена.*/
    @Override
    public String generateShortLink(String originalUrl, Optional<Duration> ttlOpt) {
        final long linkId = getNextIdAcl.getNextId();
        final Instant expiredAt = ttlOpt.map(duration -> Instant.now().plus(duration)).orElse(null);
        Link link = Link.builder()
                .id(linkId)
                .url(originalUrl)
                .token(tokenGenerator.generate(linkId))
                .createdAt(Instant.now())
                .expiredAt(expiredAt)
                .build();
        return saveLinkAcl.save(link).getToken();
    }
}
