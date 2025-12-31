package com.github.alxsshv.url_service_api.adapter.spi.forgettingoriginalurl.spring.webmvc.exception;

public class OriginalLinkServiceBadRequestException extends RuntimeException {

    public OriginalLinkServiceBadRequestException(String message) {
        super(message);
    }

    public OriginalLinkServiceBadRequestException(String message, Object... args) {
        super(String.format(message, args));
    }
}
