package com.github.alxsshv.short_link_creation_service.configuration;

import com.github.alxsshv.short_link_creation_service.adapter.out.persistence.LinkMapper;
import com.github.alxsshv.short_link_creation_service.adapter.out.persistence.LinkPersistenceAdapter;
import com.github.alxsshv.short_link_creation_service.adapter.out.persistence.LinkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceBeans {

    @Bean
    public LinkPersistenceAdapter linkPersistenceAdapter(LinkRepository linkRepository,
                                                         LinkMapper linkMapper) {
        return new LinkPersistenceAdapter(linkRepository, linkMapper);
    }

}
