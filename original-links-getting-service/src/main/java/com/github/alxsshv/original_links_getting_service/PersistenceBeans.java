package com.github.alxsshv.original_links_getting_service;

import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkMapper;
import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkPersistenceAdapter;
import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**Конфигурация для создания необходимых для работы приложения бинов для работы с БД*/
@Configuration
public class PersistenceBeans {

    @Bean
    LinkPersistenceAdapter linkPersistenceAdapter(LinkRepository linkRepository,
                                                  LinkMapper linkMapper) {
        return new LinkPersistenceAdapter(linkRepository, linkMapper);
    }

    @Bean
    LinkMapper linkMapper() {
        return new LinkMapper();
    }



}
