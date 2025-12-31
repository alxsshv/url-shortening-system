package com.github.alxsshv.original_links_getting_service.application.domain.exception;

public class LinkNotFoundException extends RuntimeException {
    public LinkNotFoundException(String message) {
        super(message);
    }

    public LinkNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
