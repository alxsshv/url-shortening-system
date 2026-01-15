package com.github.alxsshv.url_service_api.application.acl.dto;



/** DTO для запроса к внешнему сервису на создание короткой ссылки */
public record CreateShortLinkRequest(

        /** url - оригинальная ссылка.
         * Не может быть null или пустой. Должна быть длиной от 8 до 255 символов.*/
        String url,

        /** ttl - время жизни ссылки в секундах (опциональный параметр),
         *  если не указан - ссылка не имеет срока давности. */
        String ttl
) {}
