package com.github.alxsshv.original_links_getting_service.adapter.api.spring.webmvc.exception;

/** Исключение, возникающее при попытке получить токен, имеющий недопустимый формат. */
public class IllegalShortLinkTokenException extends RuntimeException {
    public IllegalShortLinkTokenException(String message) {
        super(message);
    }

    public IllegalShortLinkTokenException(String message, Object... args) {
        super(String.format(message, args));
    }
}
