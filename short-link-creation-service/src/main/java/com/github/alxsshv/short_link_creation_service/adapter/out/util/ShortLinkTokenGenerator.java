package com.github.alxsshv.short_link_creation_service.adapter.out.util;

import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;

@RequiredArgsConstructor
public class ShortLinkTokenGenerator {

    private final Hashids hashids;

    public String generate(Long id) {
        return hashids.encode(id);
    }

}
