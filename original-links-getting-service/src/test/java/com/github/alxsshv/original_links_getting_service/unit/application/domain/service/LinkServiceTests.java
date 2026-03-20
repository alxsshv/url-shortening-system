package com.github.alxsshv.original_links_getting_service.unit.application.domain.service;

import com.github.alxsshv.original_links_getting_service.application.acl.ForGettingUrlAcl;
import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import com.github.alxsshv.original_links_getting_service.application.domain.exception.LinkNotFoundException;
import com.github.alxsshv.original_links_getting_service.application.domain.service.LinkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LinkServiceTests {

    @Mock
    private ForGettingUrlAcl forGettingUrlAcl;

    @InjectMocks
    private LinkService linkService;

    @Test
    @DisplayName("Test findOriginalLink when link is found then return valid link")
    void testFindOriginalLink_whenLinkIsFound_thenReturnValidLink() {
        String token = "token";
        Link expectedlink = Link.builder()
                .id(1L)
                .token("token")
                .url(token)
                .createdAt(Instant.now())
                .expiredAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .build();
        when(forGettingUrlAcl.findLinkByToken(token)).thenReturn(expectedlink);

        Link actualLink = linkService.findOriginalLink(token);

        Assertions.assertNotNull(actualLink);
        Assertions.assertEquals(expectedlink, actualLink);
    }

    @Test
    @DisplayName("Test findOriginalLink when link is not found then throw LinkNotFoundException")
    void testFindOriginalLink_whenLinkIsNotFound_thenThrowException() {
        String token = "notFound";
        when(forGettingUrlAcl.findLinkByToken(token)).thenReturn(null);
        Assertions.assertThrows(LinkNotFoundException.class, () -> linkService.findOriginalLink(token));
    }
}
