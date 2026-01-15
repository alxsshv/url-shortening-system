package com.github.alxsshv.shortlinkcreationservice.unit.application.domain.service;

import com.github.alxsshv.shortlinkcreationservice.application.acl.GenerateShortLinkTokenAcl;
import com.github.alxsshv.shortlinkcreationservice.application.acl.GetNextIdAcl;
import com.github.alxsshv.shortlinkcreationservice.application.acl.SaveLinkAcl;
import com.github.alxsshv.shortlinkcreationservice.application.domain.service.LinkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LinkServiceTests {

    @Mock
    private GetNextIdAcl getNextIdAcl;

    @Mock
    private SaveLinkAcl saveLinkAcl;

    @Mock
    private GenerateShortLinkTokenAcl tokenGenerator;

    @InjectMocks
    private LinkService linkService;

    @Test
    @DisplayName("Test generateShortLink method")
    void testGenerateShortLink() {
        long linkId = 1L;
        String token = "token";

        when(getNextIdAcl.getNextId()).thenReturn(linkId);
        when(tokenGenerator.generate(linkId)).thenReturn(token);
        when(saveLinkAcl.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        String actualToken = linkService.generateShortLink("https://example.com", Optional.of(Duration.ofDays(1)));

        Assertions.assertNotNull(actualToken);
        Assertions.assertEquals(token, actualToken);

    }
}
