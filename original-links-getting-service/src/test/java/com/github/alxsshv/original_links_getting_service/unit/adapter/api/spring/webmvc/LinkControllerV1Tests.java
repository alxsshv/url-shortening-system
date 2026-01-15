package com.github.alxsshv.original_links_getting_service.unit.adapter.api.spring.webmvc;


import com.github.alxsshv.original_links_getting_service.adapter.api.spring.webmvc.LinkControllerV1;
import com.github.alxsshv.original_links_getting_service.adapter.api.spring.webmvc.exception.IllegalShortLinkTokenException;
import com.github.alxsshv.original_links_getting_service.application.api.ForFindOriginalUrlApi;
import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LinkControllerV1Tests {

    @Mock
    private ForFindOriginalUrlApi forFindOriginalUrlApi;

    @InjectMocks
    private LinkControllerV1 linkControllerV1;

    @Test
    @DisplayName("Test getLink method when token is correct then request status is ok")
    void testGetLinkMethod_whenTokenIsCorrect_thenRequestStatusIsOk() {
        String expectedUrl = "https://example.com";
        String token = "A1bsd5";

        Link link = Link.builder().url(expectedUrl).build();

        when(forFindOriginalUrlApi.findOriginalLink(token)).thenReturn(link);

        var result = linkControllerV1.getLink(token);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        Assertions.assertEquals(expectedUrl, result.getBody().url());
    }

    @Test
    @DisplayName("Test getLink method when token is whitespace then thrown IllegalShortLinkTokenException")
    void testGetLinkMethod_whenTokenIsWhiteSpace_thenThrownIllegalShortLinkTokenException() {
        String token = " ";

        Assertions.assertThrows(IllegalShortLinkTokenException.class, () -> linkControllerV1.getLink(token));

    }
}
