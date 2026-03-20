package com.github.alxsshv.original_links_getting_service.integration.adapter.acl.persistence;

import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkPersistenceAdapter;
import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkRepository;
import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import com.github.alxsshv.original_links_getting_service.integration.AbstractIntegrationTests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.mockito.Mockito.times;

@SpringBootTest
class LinkPersistenceAdapterIntegrationTests extends AbstractIntegrationTests {

    @MockitoSpyBean
    private LinkRepository linkRepository;

    @Autowired
    private LinkPersistenceAdapter linkPersistenceAdapter;


    @AfterEach
    void clearCacheAndDatabase() {
        cacheManager.getCache("link").clear();
        JdbcTestUtils.deleteFromTables(jdbcClient, "links.links");
    }

    @Test
    @Sql("/integration/fill_database.sql")
    @DisplayName("Test findLinkByToken when token is found then return valid link")
    void testFindLinkByToken_whenTokenIsFound_thenReturnValidLink() {
        String token = "123mvn";
        String expectedUrl = "https://mvnrepository.com/";
        Link link = linkPersistenceAdapter.findLinkByToken(token);

        Assertions.assertNotNull(link);
        Assertions.assertEquals(token, link.getToken());
        Assertions.assertEquals(expectedUrl, link.getUrl());
    }

    @Test
    @Sql("/integration/fill_database.sql")
    @DisplayName("Test findLinkByToken when token is not found then return null")
    void testFindLinkByToken_whenTokenIsNotFound_thenReturnNull() {
        String token = "notFound";
        Link link = linkPersistenceAdapter.findLinkByToken(token);

        Assertions.assertNull(link);
    }


    @Test
    @Sql("/integration/fill_database.sql")
    @DisplayName("Test findLinkByToken when cache is working then there will be one bd access")
    void testFindLinkByToken_whenCacheIsWorking_thereWillBeOneBdAccess() {
        String token = "123mvn";
        String expectedUrl = "https://mvnrepository.com/";
        linkPersistenceAdapter.findLinkByToken(token);
        linkPersistenceAdapter.findLinkByToken(token);
        Link link = linkPersistenceAdapter.findLinkByToken(token);

        Mockito.verify(linkRepository, times(1)).findByToken(token);

        Assertions.assertAll("Validating cache record",
                () -> Assertions.assertNotNull(link),
                () -> Assertions.assertEquals(token, link.getToken()),
                () -> Assertions.assertEquals(expectedUrl, link.getUrl()));
    }



}
