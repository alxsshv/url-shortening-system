package com.github.alxsshv.shortlinkcreationservice.unit.adapter.api.spring.webmvc;

import com.github.alxsshv.shortlinkcreationservice.adapter.api.spring.webmvc.LinkWebMvcAdapter;
import com.github.alxsshv.shortlinkcreationservice.adapter.api.spring.webmvc.dto.ShortLinkRequest;
import com.github.alxsshv.shortlinkcreationservice.application.api.GenerateShortLinkApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LinkWebMvcAdapterTests {

    @Mock
    private GenerateShortLinkApi generateShortLinkApi;

    @InjectMocks
    private LinkWebMvcAdapter linkWebMvcAdapter;

    @Test
    void testGenerateShortLink() {
        ShortLinkRequest request = new ShortLinkRequest("https://example.com", "PT10S");

        when(generateShortLinkApi.generateShortLink(any(), any())).thenReturn("token");
        String token = linkWebMvcAdapter.generateShortLink(request);

        Assertions.assertNotNull(token);
        Assertions.assertEquals("token", token);
        verify(generateShortLinkApi, times(1)).generateShortLink(any(), any());

    }

}
