package com.github.alxsshv.original_links_getting_service.adapter.out.persistence.util;

import lombok.RequiredArgsConstructor;

import java.time.Clock;
import java.time.Instant;

@RequiredArgsConstructor
public class DateTimeUtil {

    private final Clock clock;

    public Instant now() {
        return clock.instant();
    }
}
