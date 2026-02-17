package com.github.alxsshv.url_service_api.adapter.acl.forcreationshortlink.spring.webmvc;

import com.github.alxsshv.url_service_api.adapter.acl.utils.OtlpTraceUtils;
import com.github.alxsshv.url_service_api.application.acl.CreateShortLinkAcl;
import com.github.alxsshv.url_service_api.application.acl.dto.CreateShortLinkRequest;
import io.opentelemetry.api.trace.Span;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CreateShortLinkAclTracingDecorator implements CreateShortLinkAcl {

    private final ShortLinkCreationClient shortLinkCreationClient;

    @Override
    public String createShortLink(CreateShortLinkRequest request) {
        String traceId = Span.current().getSpanContext().getTraceId();
        String spanId = Span.current().getSpanContext().getSpanId();
        String traceparent = OtlpTraceUtils.generateTraceparentHeader(traceId, spanId);
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("traceparent", traceparent);
        return shortLinkCreationClient.createShortLink(request, headers);
    }
}
