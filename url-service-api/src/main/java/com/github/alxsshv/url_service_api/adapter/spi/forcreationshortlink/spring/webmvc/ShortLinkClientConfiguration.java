package com.github.alxsshv.url_service_api.adapter.spi.forcreationshortlink.spring.webmvc;

import com.github.alxsshv.url_service_api.ServiceConnectionProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableConfigurationProperties(ServiceConnectionProperties.class)
@RequiredArgsConstructor
public class ShortLinkClientConfiguration {

    private final ServiceConnectionProperties serviceConnectionProperties;

    @Bean
    public ShortLinkCreationClient shortLinkCreationClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(serviceConnectionProperties.shortLinkCreationServiceBaseUrl())
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(ShortLinkCreationClient.class);
    }

}
