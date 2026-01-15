package com.github.alxsshv.url_service_api.application.acl;

import com.github.alxsshv.url_service_api.application.acl.dto.CreateShortLinkRequest;

/** Интерфейс, описывающий anti-corruption layer
 * (acl - буферный слой) между приложением и сторонним сервисом в части получения оригинальной ссылки.*/
public interface CreateShortLinkAcl {

    /** Метод получения короткой ссылки.
     * @param request запрос на создание короткой ссылки в виде DTO {@link CreateShortLinkRequest}
     * @return возвращает короткую ссылку в виде строки*/
    String createShortLink(CreateShortLinkRequest request);
}
