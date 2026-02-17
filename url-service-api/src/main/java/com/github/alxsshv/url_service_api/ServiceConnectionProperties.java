package com.github.alxsshv.url_service_api;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Класс для получения базовых URL для внешних сервисов из application.properties*/
@ConfigurationProperties(prefix = "app.connections")
public record ServiceConnectionProperties (
        String originalUrlGettingServiceBaseUrl,
        String shortLinkCreationServiceBaseUrl
) {
}
