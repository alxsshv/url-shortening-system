package com.github.alxsshv.url_service_api.adapter.acl.utils;

public class OtlpTraceUtils {
    public static final String TRACEPARENT_FORMAT_VERSION = "00";

    public static String generateTraceparentHeader(String traceId, String spanId) {
        if (traceId == null || spanId == null) {
            return null;
        }
        return String.format("%s-%s-%s-%s", TRACEPARENT_FORMAT_VERSION, traceId, spanId, "01");
    }

}
