package com.github.alxsshv.shortlinkcreationservice.integration.application.domain.service;

import com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa.LinkPersistenceAdapter;
import com.github.alxsshv.shortlinkcreationservice.integration.AbstractIntegrationTest;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

class ExpiredLinkCleanServiceIntegrationTests extends AbstractIntegrationTest {

    @MockitoSpyBean
    private LinkPersistenceAdapter linkPersistenceAdapter;


    @Value("${app.clean-expired-links-delay}")
    private Duration delay;

    @Test
    @DisplayName("Test deleting expired links by scheduler")
    void testDeleteExpiredLinksByScheduler() {
        Awaitility.await().atMost(delay.plus(1, ChronoUnit.SECONDS));

        verify(linkPersistenceAdapter, atLeast(1)).deleteExpiredLinks();

    }
}
