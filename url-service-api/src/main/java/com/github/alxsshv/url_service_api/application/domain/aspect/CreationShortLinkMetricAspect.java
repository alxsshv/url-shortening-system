package com.github.alxsshv.url_service_api.application.domain.aspect;

import com.github.alxsshv.url_service_api.application.domain.metric.CreateShortLinkCountTotalMetric;
import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@AllArgsConstructor
public class CreationShortLinkMetricAspect {

    private final CreateShortLinkCountTotalMetric createShortLinkCountTotalMetric;

    @AfterReturning("execution(public * com.github.alxsshv.url_service_api.application.domain.service.UrlService.getShortLink(..))")
    public void afterCreateShortLink() {
        createShortLinkCountTotalMetric.increment();
    }

}
