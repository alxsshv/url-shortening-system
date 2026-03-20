package com.github.alxsshv.shortlinkcreationservice;


import com.github.alxsshv.shortlinkcreationservice.application.acl.DeleteExpiredLinksAcl;
import com.github.alxsshv.shortlinkcreationservice.application.acl.GenerateShortLinkTokenAcl;
import com.github.alxsshv.shortlinkcreationservice.application.acl.GetNextIdAcl;
import com.github.alxsshv.shortlinkcreationservice.application.acl.SaveLinkAcl;
import com.github.alxsshv.shortlinkcreationservice.application.domain.service.ExpiredLinkCleanService;
import com.github.alxsshv.shortlinkcreationservice.application.domain.service.LinkService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Конфигурация для создания бинов сервисов */
@Configuration
public class ServiceBeans {

    @Bean
    public LinkService linkService(SaveLinkAcl saveLinkAcl,
                                   GetNextIdAcl getNextIdAcl,
                                   GenerateShortLinkTokenAcl tokenGenerator) {
        return new LinkService(getNextIdAcl, saveLinkAcl, tokenGenerator);
    }

    @Bean
    public ExpiredLinkCleanService expiredLinkCleanService(DeleteExpiredLinksAcl deleteExpiredLinksAcl) {
        return new ExpiredLinkCleanService(deleteExpiredLinksAcl);
    }
}
