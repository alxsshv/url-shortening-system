package com.github.alxsshv.original_links_getting_service.unit.adapter.acl.cache;

import com.github.alxsshv.original_links_getting_service.adapter.acl.cache.LinkCacheTtlFunction;
import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkEntity;
import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

class LinkCacheTtlFunctionTests {

    private static final Duration DEFAULT_TTL = Duration.ofMinutes(5);

    private static final long  ACCEPTABLE_INACCURACY_IN_SECONDS = 1;

    private final LinkCacheTtlFunction linkCacheTtlFunction = new LinkCacheTtlFunction();

    private final Instant now = Instant.now();

    @BeforeEach
    void setDefaultTtl() {
        ReflectionTestUtils.setField(linkCacheTtlFunction, "defaultTtl", DEFAULT_TTL);
    }

    @Test
    @DisplayName("Test getTimeToLive method when value is valid link then return valid ttl")
    void testGetTimeToLive_whenValueIsValidLink_thenReturnValidTtl() {
        Duration expectedTtl = Duration.ofDays(365);
        String key = "key";
        Object value = Link.builder()
                .id(1L)
                .token("token")
                .url("http://url.com")
                .createdAt(now)
                .expiredAt(now.plus(expectedTtl))
                .build();

        Duration actualTtl = linkCacheTtlFunction.getTimeToLive(key, value);
        long actualInaccuracyInSeconds = expectedTtl.minus(actualTtl).abs().getSeconds();

        Assertions.assertTrue(actualInaccuracyInSeconds <= ACCEPTABLE_INACCURACY_IN_SECONDS);
    }

    @Test
    @DisplayName("Test getTimeToLive method when link is already expired then return duration zero")
    void testGetTimeToLive_whenLinkIsAlreadyExpired_thenReturnDurationZero() {
        String key = "key";
        Object value = Link.builder()
                .id(1L)
                .token("token")
                .url("http://url.com")
                .createdAt(now)
                .expiredAt(now.minus(1, ChronoUnit.DAYS))
                .build();
        Duration expectedTtl = Duration.ZERO;

        Duration actualTtl = linkCacheTtlFunction.getTimeToLive(key, value);
        long actualInaccuracyInSeconds = expectedTtl.minus(actualTtl).abs().getSeconds();

        Assertions.assertTrue(actualInaccuracyInSeconds <= ACCEPTABLE_INACCURACY_IN_SECONDS);
    }

    @Test
    @DisplayName("Test getTimeToLive method when value is not link then return default ttl")
    void testGetTimeToLive_whenValueIsNotLink_thenReturnDefaultTtl() {
        String key = "key";
        Object value = new LinkEntity();
        Duration expectedTtl = DEFAULT_TTL;

        Duration actualTtl = linkCacheTtlFunction.getTimeToLive(key, value);
        long actualInaccuracyInSeconds = expectedTtl.minus(actualTtl).abs().getSeconds();

        Assertions.assertTrue(actualInaccuracyInSeconds <= ACCEPTABLE_INACCURACY_IN_SECONDS);
    }

}
