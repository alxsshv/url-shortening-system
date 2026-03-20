package com.github.alxsshv.original_links_getting_service;

import com.github.alxsshv.original_links_getting_service.application.domain.service.LinkService;
import com.github.alxsshv.original_links_getting_service.application.acl.ForGettingUrlAcl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**Конфигурация для создания необходимых для работы приложения бинов*/
@Configuration
public class ServiceBeans {

    @Bean
    LinkService linkService(ForGettingUrlAcl forGettingUrlAcl) {
        return new LinkService(forGettingUrlAcl);
    }
}
