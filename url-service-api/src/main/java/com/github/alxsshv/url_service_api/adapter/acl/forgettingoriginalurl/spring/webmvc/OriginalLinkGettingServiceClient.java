package com.github.alxsshv.url_service_api.adapter.acl.forgettingoriginalurl.spring.webmvc;

import com.github.alxsshv.url_service_api.application.acl.dto.Url;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Map;

/** Адаптер для получения оригинального URL по короткому из внешнего сервиса */
@HttpExchange(url = "/api/v1/links")
public interface OriginalLinkGettingServiceClient {

    /** Метод получения оригинального URL по токену короткой ссылки.
     * @param shortLink - токен короткой ссылки.
     * @return возвращает объект {@link Url}, содержащий оригинальную Url*/
    @GetExchange("/{shortLink}")
    Url getOriginalUri(@PathVariable String shortLink,
                       @RequestHeader(required = false) Map<String, String> headers);

}
