package com.github.alxsshv.url_service_api.adapter.api.spring.webmvc;

import com.github.alxsshv.url_service_api.adapter.acl.forgettingoriginalurl.spring.webmvc.exception.OriginalUrlNotFoundException;
import com.github.alxsshv.url_service_api.adapter.api.spring.webmvc.dto.ServiceMessage;
import com.github.alxsshv.url_service_api.adapter.api.spring.webmvc.dto.ShortLinkRequest;
import com.github.alxsshv.url_service_api.application.api.GetOriginalUrlApi;
import com.github.alxsshv.url_service_api.application.api.GetShortLinkApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.view.RedirectView;

import static org.mockito.Mockito.when;

@WebMvcTest
@AutoConfigureRestTestClient
class LinkControllerV1Tests {

    @Autowired
    private RestTestClient restTestClient;

    @MockitoBean
    private GetOriginalUrlApi originalUrlApi;

    @MockitoBean
    private GetShortLinkApi shortLinkApi;

    @Value("${app.short-link-url-address}")
    private String shortLinkUrlAddress;


    @Test
    @DisplayName("Test getOriginalUrl when received valid short link then return valid original url")
    void testGetOriginalUrl_whenReceivedValidShortLink_thenReturnValidOriginalUrl() {
        String shortLink = "mvn123";
        String originalUrl = "https://www.google.com";

        when(originalUrlApi.getOriginalUrl(shortLink)).thenReturn(originalUrl);

        String result = restTestClient.get()
                .uri("/link/" + shortLink)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.SEE_OTHER)
                .expectBody(String.class).returnResult().getResponseBody();

        Assertions.assertEquals(originalUrl, result);
    }

    @Test
    @DisplayName("Test getOriginalUrl when short link's length less than six chars then return bad request status and message")
    void testGetOriginalUrl_whenShortLinksLengthLessThanSixChars_thenReturnBadRequestStatus() {
        String shortLink = "msdf1";
        String message = "invalid short link size";

        String result = restTestClient.get()
                .uri("/link/" + shortLink)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
                .expectBody(ServiceMessage.class)
                .returnResult()
                .getResponseBody().message();

        Assertions.assertEquals(message, result);
    }

    @Test
    @DisplayName("Test getOriginalUrl when short link's length more than six chars then return bad request status and message")
    void testGetOriginalUrl_whenShortLinksLengthMoreThanSixChars_thenReturnBadRequestStatus() {
        String shortLink = "msdf132";
        String message = "invalid short link size";

        String result = restTestClient.get()
                .uri("/link/" + shortLink)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST)
                .expectBody(ServiceMessage.class)
                .returnResult()
                .getResponseBody().message();

        Assertions.assertEquals(message, result);
    }

    @Test
    @DisplayName("Test getOriginalUrl when short link not found then return not found status and message")
    void testGetOriginalUrl_whenShortLinkNotFound_thenReturnNotFoundStatus() {
        String shortLink = "nfound";
        String message = "Url not found or expired";

        when(originalUrlApi.getOriginalUrl(shortLink)).thenThrow(OriginalUrlNotFoundException.class);

        String result = restTestClient.get()
                .uri("/link/" + shortLink)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND)
                .expectBody(ServiceMessage.class)
                .returnResult()
                .getResponseBody().message();

        Assertions.assertEquals(message, result);
    }


    @Test
    @DisplayName("Test redirectOriginalUrl when received valid short link then return valid redirect view")
    void testRedirectOriginalUrl_whenReceivedValidShortLink_thenReturnValidRedirectView() {
        String shortLink = "mvn123";
        String originalUrl = "https://www.google.com";

        when(originalUrlApi.getOriginalUrl(shortLink)).thenReturn(originalUrl);

        String url = restTestClient.get()
                .uri("/" + shortLink)
                .exchange()
                .expectStatus().isFound()
                .returnResult(RedirectView.class)
                .getResponseHeaders().getLocation().toString();
        Assertions.assertEquals(originalUrl, url);
    }

    @Test
    @DisplayName("Test createShortLink when received valid short link request then return valid short link url")
    void testCreateShortLink_whenReceivedValidShortLinkRequest_thenReturnValidShortLinkUrl() {
        String originalUrl = "https://www.google.com";
        String shortLinkToken = "g00glE";
        String expectedUrl = shortLinkUrlAddress + shortLinkToken;
        ShortLinkRequest request = new ShortLinkRequest(originalUrl, "PT10H");

        when(shortLinkApi.getShortLink(request.url(),request.ttl())).thenReturn(shortLinkToken);

        String actualUrl = restTestClient.post()
                .uri("/link")
                .header("Content-Type", "application/json")
                .body(request)
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class).getResponseBody();

        Assertions.assertNotNull(actualUrl);
        Assertions.assertEquals(expectedUrl, actualUrl);

    }

    @Test
    @DisplayName("Test createShortLink when short link requests url is null then return bad request status")
    void testCreateShortLink_whenShortLinkRequestsUrlIsNull_thenReturnBadRequestStatus() {
        ShortLinkRequest request = new ShortLinkRequest(null, "PT10H");
        String expectedMessage = "Invalid value for field url: URL cannot be null or empty";

        String message = restTestClient.post()
                .uri("/link")
                .header("Content-Type", "application/json")
                .body(request)
                .exchange()
                .expectStatus().isBadRequest()
                .returnResult(ServiceMessage.class)
                .getResponseBody().message();

        Assertions.assertNotNull(message);
        Assertions.assertEquals(expectedMessage, message);
    }

    @Test
    @DisplayName("Test createShortLink when original url has invalid format then return bad request status")
    void testCreateShortLink_whenOriginalUrlHasInvalidFormat_thenReturnBadRequestStatus() {
        String originalUrl = "w.google";
        ShortLinkRequest request = new ShortLinkRequest(originalUrl, "PT10H");
        String expectedMessage = "Invalid value for field url: Invalid URL";

        String message = restTestClient.post()
                .uri("/link")
                .header("Content-Type", "application/json")
                .body(request)
                .exchange()
                .expectStatus().isBadRequest()
                .returnResult(ServiceMessage.class)
                .getResponseBody().message();

        Assertions.assertNotNull(message);
        Assertions.assertEquals(expectedMessage, message);
    }

    @Test
    @DisplayName("Test createShortLink when original url length is more than 255 then return bad request status")
    void testCreateShortLink_whenOriginalUrlsLengthIsMoreThan255_thenReturnBadRequestStatus() {
        String originalUrl = "https://www.gooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooogle.com";
        String expectedMessage = "Invalid value for field url: URL must contain between 8 and 255 characters";
        ShortLinkRequest request = new ShortLinkRequest(originalUrl, "PT10H");

        String message = restTestClient.post()
                .uri("/link")
                .header("Content-Type", "application/json")
                .body(request)
                .exchange()
                .expectStatus().isBadRequest()
                .returnResult(ServiceMessage.class)
                .getResponseBody().message();


        Assertions.assertNotNull(message);
        Assertions.assertEquals(expectedMessage, message);
    }

    @Test
    @DisplayName("Test createShortLink when ttl is not valid then return bad request status")
    void testCreateShortLink_whenTtlIsNotValid_thenReturnBadRequestStatus() {
        String originalUrl = "https://www.google.com";
        String ttl = "2PT10H10M";
        String expectedMessage = "Invalid value for field ttl: String must be in duration format ISO 8601";
        ShortLinkRequest request = new ShortLinkRequest(originalUrl, ttl);


        String message = restTestClient.post()
                .uri("/link")
                .header("Content-Type", "application/json")
                .body(request)
                .exchange()
                .expectStatus().isBadRequest()
                .returnResult(ServiceMessage.class)
                .getResponseBody().message();

        Assertions.assertNotNull(message);
        Assertions.assertEquals(expectedMessage, message);
    }

    @Test
    @DisplayName("Test createShortLink when short link creation service unavailable then return service unavailable status")
    void testCreateShortLink_whenShortLinkCreationServiceUnavailable_thenReturnServiceUnavailableStatus() {

        String expectedMessage = "Operation is temporarily unavailable";
        ShortLinkRequest request = new ShortLinkRequest("https://www.google.com", "PT10H");

        when(shortLinkApi.getShortLink(request.url(),request.ttl())).thenThrow(ResourceAccessException.class);

        String message = restTestClient.post()
                .uri("/link")
                .header("Content-Type", "application/json")
                .body(request)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.SERVICE_UNAVAILABLE)
                .returnResult(ServiceMessage.class)
                .getResponseBody().message();

        Assertions.assertNotNull(message);
        Assertions.assertEquals(expectedMessage, message);
    }



}
