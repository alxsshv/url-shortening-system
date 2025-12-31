package com.github.alxsshv.url_service_api;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "connections")
public record ServiceConnectionProperties (
        String originalUrlGettingServiceBaseUrl,
        String shortLinkCreationServiceBaseUrl
) {
}
