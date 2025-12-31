package com.github.alxsshv.short_link_creation_service.application.port.in;

import java.time.Duration;
import java.util.Optional;

public interface GenerateShortLinkApi {

    String generateShortLink(String originalUrl, Optional<Duration> ttlOptional);
}
