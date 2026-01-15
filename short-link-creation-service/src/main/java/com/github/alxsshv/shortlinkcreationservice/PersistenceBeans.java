package com.github.alxsshv.shortlinkcreationservice;

import com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa.LinkMapper;
import com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa.LinkPersistenceAdapter;
import com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa.LinkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Конфигурация для создания бинов для работы с БД */
@Configuration
public class PersistenceBeans {

    @Bean
    public LinkPersistenceAdapter linkPersistenceAdapter(LinkRepository linkRepository,
                                                         LinkMapper linkMapper) {
        return new LinkPersistenceAdapter(linkRepository, linkMapper);
    }

    @Bean
    public LinkMapper linkMapper() {
        return new LinkMapper();
    }

}
