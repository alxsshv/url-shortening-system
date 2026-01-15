package com.github.alxsshv.shortlinkcreationservice.integration.adapter.acl.spring.data.jpa;

import com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa.LinkPersistenceAdapter;
import com.github.alxsshv.shortlinkcreationservice.application.domain.model.Link;
import com.github.alxsshv.shortlinkcreationservice.integration.AbstractIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

class LinkPersistenceAdapterIntegrationTests extends AbstractIntegrationTest {

    @Autowired
    private LinkPersistenceAdapter linkPersistenceAdapter;


    @Test
    @DisplayName("test getNextId method when invoke method then return valid result")
    void testGetNext_whenInvokeMethod_thenReturnValidResult() {
        long actualId = linkPersistenceAdapter.getNextId();
        Assertions.assertTrue(actualId > 0);
    }


    @Test
    @DisplayName("test getNextId method when method called multiple times then each result is unique")
    void testGetNext_whenMethodCalledMultipleTimes_thenEachResultIsUnique() {
        long firstId = linkPersistenceAdapter.getNextId();
        long secondId = linkPersistenceAdapter.getNextId();
        long lastId = linkPersistenceAdapter.getNextId();

        Assertions.assertTrue(firstId > 0);
        Assertions.assertTrue(secondId > firstId);
        Assertions.assertTrue(lastId > secondId);
    }

    @Test
    @DisplayName("test save method when save valid Link then return saved Link")
    void testSave_whenSaveValidLink_thenReturnSavedLink() {
        long id = 1L;
        Link expectedlink = Link.builder()
                .id(id)
                .token("token")
                .url("http://example.com")
                .createdAt(Instant.now())
                .expiredAt(Instant.now().plus(Duration.ofDays(1)))
                .build();

        Link actualLink = linkPersistenceAdapter.save(expectedlink);
        Link linkFromDb = jdbcClient.sql("SELECT * FROM links.links WHERE id = " + id).query(Link.class).single();

        Assertions.assertEquals(expectedlink, actualLink);
        Assertions.assertNotNull(linkFromDb);
    }

    @Test
    @DisplayName("test deleteExpiredLinks method when invoke method then delete all expired links")
    void testDeleteExpiredLinks_whenInvokeMethod_thenDeleteAllExpiredLinks() {
        long id = 3L;
        Link link = Link.builder()
                .id(id)
                .token("token")
                .url("http://example.com")
                .createdAt(Instant.now())
                .expiredAt(Instant.now())
                .build();

        linkPersistenceAdapter.save(link);
        long beforeDeleteCount  = jdbcClient.sql("SELECT COUNT(*) FROM links.links").query(Long.class).single();
        linkPersistenceAdapter.deleteExpiredLinks();
        long afterDeleteCount  = jdbcClient.sql("SELECT COUNT(*) FROM links.links").query(Long.class).single();

        Optional<Link> linkFromDb = jdbcClient.sql("SELECT * FROM links.links WHERE id = " + id).query(Link.class).optional();

        Assertions.assertTrue(beforeDeleteCount > afterDeleteCount);
        Assertions.assertTrue(linkFromDb.isEmpty());
    }

}
