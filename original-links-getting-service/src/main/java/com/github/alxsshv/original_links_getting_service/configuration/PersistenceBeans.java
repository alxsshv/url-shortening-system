package com.github.alxsshv.original_links_getting_service.configuration;

import com.github.alxsshv.original_links_getting_service.adapter.out.persistence.LinkMapper;
import com.github.alxsshv.original_links_getting_service.adapter.out.persistence.LinkPersistenceAdapter;
import com.github.alxsshv.original_links_getting_service.adapter.out.persistence.LinkRepository;
import com.github.alxsshv.original_links_getting_service.adapter.out.persistence.util.DateTimeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class PersistenceBeans {

    @Bean
    LinkPersistenceAdapter linkPersistenceAdapter(LinkRepository linkRepository,
                                                  LinkMapper linkMapper) {
        return new LinkPersistenceAdapter(linkRepository, linkMapper);
    }

    @Bean
    DateTimeUtil dateTimeUtil(Clock clock) {
        return new DateTimeUtil(clock);
    }

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

}
