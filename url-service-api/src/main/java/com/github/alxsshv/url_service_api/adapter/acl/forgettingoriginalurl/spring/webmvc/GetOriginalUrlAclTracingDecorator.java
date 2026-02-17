package com.github.alxsshv.url_service_api.adapter.acl.forgettingoriginalurl.spring.webmvc;


import com.github.alxsshv.url_service_api.adapter.acl.utils.OtlpTraceUtils;
import com.github.alxsshv.url_service_api.application.acl.GetOriginalUrlAcl;
import com.github.alxsshv.url_service_api.application.acl.dto.Url;
import io.opentelemetry.api.trace.Span;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GetOriginalUrlAclTracingDecorator implements GetOriginalUrlAcl {

    private final OriginalLinkGettingServiceClient linkGettingServiceClient;

    @Override
    public Url getOriginalUri(String shortLink) {
        String traceId = Span.current().getSpanContext().getTraceId();
        String spanId = Span.current().getSpanContext().getSpanId();
        String traceparent = OtlpTraceUtils.generateTraceparentHeader(traceId, spanId);
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("traceparent", traceparent);
        return linkGettingServiceClient.getOriginalUri(shortLink, headers);
    }

}
