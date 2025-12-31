package com.github.alxsshv.original_links_getting_service.configuration;

import com.github.alxsshv.original_links_getting_service.application.domain.service.LinkService;
import com.github.alxsshv.original_links_getting_service.application.port.out.FindLinkByTokenUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeans {

    @Bean
    LinkService linkService(FindLinkByTokenUseCase findLinkByTokenUseCase) {
        return new LinkService(findLinkByTokenUseCase);
    }
}
