package com.github.alxsshv.url_service_api.application.domain.service;

import com.github.alxsshv.url_service_api.application.acl.CreateShortLinkAcl;
import com.github.alxsshv.url_service_api.application.acl.GetOriginalUrlAcl;
import com.github.alxsshv.url_service_api.application.acl.dto.Url;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlServiceTests {

    @Mock
    private CreateShortLinkAcl createShortLinkAcl;

    @Mock
    private GetOriginalUrlAcl getOriginalUrlAcl;

    @InjectMocks
    private UrlService urlService;

    @Test
    void testGetOriginalUrl() {
        String shortLink = "123tok";
        Url expectedUrl = new Url("https://google.com");
        when(getOriginalUrlAcl.getOriginalUri(shortLink)).thenReturn(expectedUrl);

        String actualUrl = urlService.getOriginalUrl(shortLink);

        Assertions.assertEquals(expectedUrl.url(), actualUrl);
        verify(getOriginalUrlAcl, times(1)).getOriginalUri(shortLink);
    }

    @Test
    void getShortLink() {
        String originalUrl = "https://google.com";
        String ttl = "PT10H";
        String shortLink = "123tok";

        when(createShortLinkAcl.createShortLink(any())).thenReturn(shortLink);

        String actualShortLink = urlService.getShortLink(originalUrl, ttl);

        Assertions.assertEquals(shortLink, actualShortLink);
    }
}
