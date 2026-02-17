package com.github.alxsshv.url_service_api;

import com.github.alxsshv.url_service_api.application.domain.aspect.CreationShortLinkMetricAspect;
import com.github.alxsshv.url_service_api.application.domain.metric.CreateShortLinkCountTotalMetric;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**Конфигурация для создания необходимых для работы приложения бинов*/
@Configuration
public class ServiceBeans {


    @Bean
    CreateShortLinkCountTotalMetric createShortLinkCountTotalMetric(MeterRegistry meterRegistry) {
        return new CreateShortLinkCountTotalMetric(meterRegistry);
    }

    @Bean
    CreationShortLinkMetricAspect creationShortLinkMetricAspect(CreateShortLinkCountTotalMetric createShortLinkCountTotalMetric) {
        return new CreationShortLinkMetricAspect(createShortLinkCountTotalMetric);
    }

}
