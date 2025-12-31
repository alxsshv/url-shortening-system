package com.github.alxsshv.url_service_api.application.port.api;

public interface GetShortLinkApi {

    String getShortLink(String originalUrl, String ttl);

}
