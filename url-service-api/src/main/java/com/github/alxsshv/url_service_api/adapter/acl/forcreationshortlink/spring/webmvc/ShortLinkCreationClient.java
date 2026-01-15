package com.github.alxsshv.url_service_api.adapter.acl.forcreationshortlink.spring.webmvc;

import com.github.alxsshv.url_service_api.application.acl.CreateShortLinkAcl;
import com.github.alxsshv.url_service_api.application.acl.dto.CreateShortLinkRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/** Адаптер для взаимодействия приложения с внешним сервисом для создания коротких ссылок */
@HttpExchange(url = "/api/v1/links")
public interface ShortLinkCreationClient extends CreateShortLinkAcl {

    /** Метод получения короткой ссылки.
     * @param request запрос на создание короткой ссылки в виде DTO {@link CreateShortLinkRequest}
     * @return возвращает короткую ссылку в виде строки*/
    @Override
    @PostExchange
    String createShortLink(@RequestBody CreateShortLinkRequest request);


}
