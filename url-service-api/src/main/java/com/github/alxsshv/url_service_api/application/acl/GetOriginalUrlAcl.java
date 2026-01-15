package com.github.alxsshv.url_service_api.application.acl;

import com.github.alxsshv.url_service_api.application.acl.dto.Url;

/** Интерфейс, описывающий anti-corruption layer
 * (acl - буферный слой) между приложением и сторонним сервисом в части получения оригинальной ссылки.*/
public interface GetOriginalUrlAcl {

    /** Метод получения оригинального URL по токену (уникальной части) короткой ссылки.
     * @param shortLink - токен короткой ссылки.
     * @return возвращает объект {@link Url}, содержащий оригинальную Url */
    Url getOriginalUri(String shortLink);

}
