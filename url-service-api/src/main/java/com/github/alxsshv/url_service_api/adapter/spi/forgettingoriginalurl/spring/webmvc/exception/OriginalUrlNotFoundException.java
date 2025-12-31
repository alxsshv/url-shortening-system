package com.github.alxsshv.url_service_api.adapter.spi.forgettingoriginalurl.spring.webmvc.exception;

public class OriginalUrlNotFoundException extends RuntimeException {
    public OriginalUrlNotFoundException(String message) {
        super(message);
    }

    public OriginalUrlNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
}
