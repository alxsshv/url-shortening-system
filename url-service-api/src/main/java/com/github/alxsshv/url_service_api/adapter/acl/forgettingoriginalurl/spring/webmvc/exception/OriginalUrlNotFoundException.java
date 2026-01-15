package com.github.alxsshv.url_service_api.adapter.acl.forgettingoriginalurl.spring.webmvc.exception;

/** Исключение, возникающее в случае, если оригинальный URL не найден на стороне внешнего сервиса */
public class OriginalUrlNotFoundException extends RuntimeException {
    public OriginalUrlNotFoundException(String message) {
        super(message);
    }

    public OriginalUrlNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
