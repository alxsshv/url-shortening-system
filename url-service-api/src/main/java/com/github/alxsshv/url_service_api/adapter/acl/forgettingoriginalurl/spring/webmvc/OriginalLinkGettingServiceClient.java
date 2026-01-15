package com.github.alxsshv.url_service_api.adapter.acl.forgettingoriginalurl.spring.webmvc;

import com.github.alxsshv.url_service_api.application.acl.dto.Url;
import com.github.alxsshv.url_service_api.application.acl.GetOriginalUrlAcl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/** Адаптер для получения оригинального URL по короткому из внешнего сервиса */
@HttpExchange(url = "/api/v1/links")
public interface OriginalLinkGettingServiceClient extends GetOriginalUrlAcl {

    /** Метод получения оригинального URL по токену короткой ссылки.
     * @param shortLink - токен короткой ссылки.
     * @return возвращает объект {@link Url}, содержащий оригинальную Url*/
    @Override
    @GetExchange("/{shortLink}")
    Url getOriginalUri(@PathVariable String shortLink);

}
