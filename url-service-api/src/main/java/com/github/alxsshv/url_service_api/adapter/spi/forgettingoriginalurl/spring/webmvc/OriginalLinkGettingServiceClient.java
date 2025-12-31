package com.github.alxsshv.url_service_api.adapter.spi.forgettingoriginalurl.spring.webmvc;

import com.github.alxsshv.url_service_api.application.port.spi.dto.Url;
import com.github.alxsshv.url_service_api.application.port.spi.GetOriginalUrlSpi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/v1/links")
public interface OriginalLinkGettingServiceClient extends GetOriginalUrlSpi {

    @Override
    @GetExchange("/{shortLink}")
    Url getOriginalUri(@PathVariable String shortLink);

}
