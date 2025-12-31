package com.github.alxsshv.short_link_creation_service.configuration;

import com.github.alxsshv.short_link_creation_service.adapter.out.util.ShortLinkTokenGenerator;
import com.github.alxsshv.short_link_creation_service.application.domain.service.LinkService;
import com.github.alxsshv.short_link_creation_service.application.port.out.persistence.GetNextIdUseCase;
import com.github.alxsshv.short_link_creation_service.application.port.out.persistence.SaveLinkUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeans {

    @Bean
    public LinkService linkService(SaveLinkUseCase saveLinkUseCase,
                                   GetNextIdUseCase getNextIdUseCase,
                                   ShortLinkTokenGenerator tokenGenerator) {
        return new LinkService(getNextIdUseCase, saveLinkUseCase, tokenGenerator);
    }
}
