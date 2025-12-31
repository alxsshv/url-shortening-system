package com.github.alxsshv.url_service_api.application.port.spi;

import com.github.alxsshv.url_service_api.application.port.spi.dto.Url;

public interface GetOriginalUrlSpi {

    Url getOriginalUri(String shortLink);

}
