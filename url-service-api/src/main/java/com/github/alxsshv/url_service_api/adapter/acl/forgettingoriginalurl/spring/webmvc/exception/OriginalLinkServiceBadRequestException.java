package com.github.alxsshv.url_service_api.adapter.acl.forgettingoriginalurl.spring.webmvc.exception;

/** Исключение, возникающее при ошибке валидации запроса на стороне внешнего сервиса
 *  для получения оригинальных ссылок */
public class OriginalLinkServiceBadRequestException extends RuntimeException {

    public OriginalLinkServiceBadRequestException(String message) {
        super(message);
    }

    public OriginalLinkServiceBadRequestException(String message, Object... args) {
        super(String.format(message, args));
    }
}
