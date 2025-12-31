package com.github.alxsshv.url_service_api.application.port.spi;

import com.github.alxsshv.url_service_api.application.port.spi.dto.CreateShortLinkRequest;

public interface CreateShortLinkSpi {

    String createShortLink(CreateShortLinkRequest request);
}
