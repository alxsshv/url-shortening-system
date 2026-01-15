package com.github.alxsshv.shortlinkcreationservice.unit.adapter.acl.spring.data.jpa;

import com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa.LinkEntity;
import com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa.LinkMapper;
import com.github.alxsshv.shortlinkcreationservice.application.domain.model.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;


class LinkMapperTest {


    private final LinkMapper linkMapper = new LinkMapper();

    private final Link link = Link.builder()
            .id(1L)
            .token("token1")
            .url("http://example.com")
            .createdAt(Instant.now())
            .expiredAt(Instant.now().plus(Duration.ofDays(1)))
            .build();

    private final LinkEntity linkEntity = new LinkEntity(
            true, link.getId(), link.getToken(), link.getUrl(), link.getCreatedAt(), link.getExpiredAt());


    @Test
    @DisplayName("Test toEntity method")
    void testToEntity() {
        LinkEntity actualLinkEntity = linkMapper.toEntity(link);

        Assertions.assertAll(
                () -> Assertions.assertTrue(actualLinkEntity.isNew()),
                () -> Assertions.assertEquals(link.getId(), actualLinkEntity.getId()),
                () -> Assertions.assertEquals(link.getToken(), actualLinkEntity.getToken()),
                () -> Assertions.assertEquals(link.getUrl(), actualLinkEntity.getUrl()),
                () -> Assertions.assertEquals(link.getCreatedAt(), actualLinkEntity.getCreatedAt()),
                () -> Assertions.assertEquals(link.getExpiredAt(), actualLinkEntity.getExpiredAt()));

    }

    @Test
    @DisplayName("Test toDomain method")
    void testToDomain() {
        Link actualLink = linkMapper.toDomain(linkEntity);

        Assertions.assertEquals(link, actualLink);
    }
}
