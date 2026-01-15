package com.github.alxsshv.url_service_api.application.domain.metric;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;


public class CreateShortLinkCountTotalMetric {

    public static final String CREATE_SHORT_LINK_COUNT_TOTAL = "create_short_link_count_total";

    private final Counter counter;

    public CreateShortLinkCountTotalMetric(MeterRegistry meterRegistry) {
        counter = Counter.builder(CREATE_SHORT_LINK_COUNT_TOTAL)
                .register(meterRegistry);
    }

    public void increment() {
        counter.increment();
    }
}
