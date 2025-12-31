package com.github.alxsshv.url_service_api.adapter.spi.forcreationshortlink.spring.webmvc;

import com.github.alxsshv.url_service_api.application.port.spi.CreateShortLinkSpi;
import com.github.alxsshv.url_service_api.application.port.spi.dto.CreateShortLinkRequest;
import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url = "/v1/links")
public interface ShortLinkCreationClient extends CreateShortLinkSpi {

    @Override
    @PostExchange
    String createShortLink(@RequestBody CreateShortLinkRequest request);


}
