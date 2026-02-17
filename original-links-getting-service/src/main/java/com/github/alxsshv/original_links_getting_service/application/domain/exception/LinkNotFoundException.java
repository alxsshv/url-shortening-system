package com.github.alxsshv.original_links_getting_service.application.domain.exception;

/** Исключение, выбрасываемое, в случае если ссылка не найдена. */
public class LinkNotFoundException extends RuntimeException {
    public LinkNotFoundException(String message) {
        super(message);
    }

    public LinkNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
