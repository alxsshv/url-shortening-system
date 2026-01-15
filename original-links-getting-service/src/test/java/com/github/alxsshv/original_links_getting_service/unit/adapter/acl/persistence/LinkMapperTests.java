package com.github.alxsshv.original_links_getting_service.unit.adapter.acl.persistence;

import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkEntity;
import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkMapper;
import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class LinkMapperTests {

    private final LinkMapper linkMapper = new LinkMapper();

    @Test
    @DisplayName("Test toDomain when linkEntity is not null")
    void testToDomain_whenLinkEntityNotNull() {
        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setId(4L);
        linkEntity.setToken("token");
        linkEntity.setUrl("http://url.com");
        linkEntity.setCreatedAt(Instant.now());
        linkEntity.setExpiredAt(Instant.now().plus(10, ChronoUnit.DAYS));

        Link expectedlink = Link.builder()
                .id(linkEntity.getId())
                .token(linkEntity.getToken())
                .url(linkEntity.getUrl())
                .createdAt(linkEntity.getCreatedAt())
                .expiredAt(linkEntity.getExpiredAt())
                .build();

        Link actualLink = linkMapper.toDomain(linkEntity);

        Assertions.assertEquals(expectedlink, actualLink);
    }

    @Test
    @DisplayName("Test toDomain when linkEntity is not null")
    void testToDomain_whenLinkEntityIsNull() {

        Assertions.assertNull(linkMapper.toDomain(null));
    }
}
