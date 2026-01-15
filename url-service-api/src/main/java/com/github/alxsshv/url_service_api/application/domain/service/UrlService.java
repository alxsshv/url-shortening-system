package com.github.alxsshv.url_service_api.application.domain.service;

import com.github.alxsshv.url_service_api.application.api.GetOriginalUrlApi;
import com.github.alxsshv.url_service_api.application.api.GetShortLinkApi;
import com.github.alxsshv.url_service_api.application.acl.CreateShortLinkAcl;
import com.github.alxsshv.url_service_api.application.acl.GetOriginalUrlAcl;
import com.github.alxsshv.url_service_api.application.acl.dto.CreateShortLinkRequest;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;

/** Сервис для создания коротких ссылок и получения оригинальных ссылок */
@AllArgsConstructor
public class UrlService implements GetOriginalUrlApi, GetShortLinkApi {

    /** Буферный слой между приложением и сторонним сервисом для создания коротких ссылок */
    private final CreateShortLinkAcl createShortLinkAcl;

    /** Буферный слой между приложением и сторонним сервисом для получения оригинальных ссылок */
    private final GetOriginalUrlAcl getOriginalUrlAcl;

    /** Метод получения оригинальной ссылки по короткой.
     * @param shortUrl - токен (уникальная часть) короткой ссылки.
     * @return строку, содержащую оригинальную ссылку*/
    @Override
    @WithSpan("urlService.getOriginalUrl")
    public String getOriginalUrl(String shortUrl) {
        return getOriginalUrlAcl.getOriginalUri(shortUrl).url();
    }

    /** Метод создания короткой ссылки.
     * @param originalUrl - оригинальная ссылка в виде строки.
     * @param ttl - время жизни короткой ссылки в формате ISO-8601.
     * @return возвращает токен (уникальную часть) короткой ссылки.*/
    @Override
    @WithSpan("urlService.getShortLink")
    public String getShortLink(String originalUrl,  @Nullable String ttl) {
        return createShortLinkAcl.createShortLink(new CreateShortLinkRequest(originalUrl, ttl));
    }
}
