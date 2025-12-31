package com.github.alxsshv.url_service_api;

import com.github.alxsshv.url_service_api.application.domain.service.UrlService;
import com.github.alxsshv.url_service_api.application.port.spi.CreateShortLinkSpi;
import com.github.alxsshv.url_service_api.application.port.spi.GetOriginalUrlSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeans {

    @Bean
    public UrlService urlService(CreateShortLinkSpi createShortLinkSpi,
                                 GetOriginalUrlSpi originalUrlSpi) {
        return new UrlService(createShortLinkSpi, originalUrlSpi);
    }

}
