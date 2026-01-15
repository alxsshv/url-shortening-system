package com.github.alxsshv.url_service_api.adapter.acl.forgettingoriginalurl.spring.webmvc;

import com.github.alxsshv.url_service_api.ServiceConnectionProperties;
import com.github.alxsshv.url_service_api.application.acl.dto.ErrorMessage;
import com.github.alxsshv.url_service_api.adapter.acl.forgettingoriginalurl.spring.webmvc.exception.OriginalLinkServiceBadRequestException;
import com.github.alxsshv.url_service_api.adapter.acl.forgettingoriginalurl.spring.webmvc.exception.OriginalUrlNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import tools.jackson.databind.ObjectMapper;

/**  Конфигурация адаптера для получения оригинального URL по короткому из внешнего сервиса  */
@Configuration
@EnableConfigurationProperties(ServiceConnectionProperties.class)
@RequiredArgsConstructor
public class OriginalLinkClientConfiguration {

    /** Параметры подключения к внешним сервисам из application.yml */
    private final ServiceConnectionProperties serviceConnectionProperties;

    @Bean
    public OriginalLinkGettingServiceClient originalLinkGettingServiceClient(ObjectMapper objectMapper) {
        RestClient restClient = RestClient.builder()
                .baseUrl(serviceConnectionProperties.originalUrlGettingServiceBaseUrl())
                .defaultStatusHandler(status -> status.equals(HttpStatus.NOT_FOUND),
                        (request, response) -> {
                            throw new OriginalUrlNotFoundException("Url not found for uri:" + request.getURI());
                        })
                .defaultStatusHandler(status -> status.equals(HttpStatus.BAD_REQUEST),
                        (request, response) -> {
                            ErrorMessage errorMessage = objectMapper.readValue(response.getBody(), ErrorMessage.class);
                            throw new OriginalLinkServiceBadRequestException("Bad request for original link getting service: %s," +
                                    " url: %s", errorMessage.message(), request.getURI());
                        })
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(OriginalLinkGettingServiceClient.class);
    }
}
