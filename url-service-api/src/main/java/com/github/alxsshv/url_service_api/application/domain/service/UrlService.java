package com.github.alxsshv.url_service_api.application.domain.service;

import com.github.alxsshv.url_service_api.application.port.api.GetOriginalUrlApi;
import com.github.alxsshv.url_service_api.application.port.api.GetShortLinkApi;
import com.github.alxsshv.url_service_api.application.port.spi.CreateShortLinkSpi;
import com.github.alxsshv.url_service_api.application.port.spi.GetOriginalUrlSpi;
import com.github.alxsshv.url_service_api.application.port.spi.dto.CreateShortLinkRequest;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UrlService implements GetOriginalUrlApi, GetShortLinkApi {

    private final CreateShortLinkSpi createShortLinkSpi;

    private final GetOriginalUrlSpi getOriginalUrlSpi;

    @Override
    public String getOriginalUrl(String shortUrl) {
        return getOriginalUrlSpi.getOriginalUri(shortUrl).url();
    }


    @Override
    public String getShortLink(String originalUrl,  @Nullable String ttl) {
        return createShortLinkSpi.createShortLink(new CreateShortLinkRequest(originalUrl, ttl));
    }
}
