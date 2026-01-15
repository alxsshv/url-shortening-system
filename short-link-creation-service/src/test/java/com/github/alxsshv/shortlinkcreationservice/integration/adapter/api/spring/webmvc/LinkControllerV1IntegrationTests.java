package com.github.alxsshv.shortlinkcreationservice.integration.adapter.api.spring.webmvc;

import com.github.alxsshv.shortlinkcreationservice.adapter.acl.hashids.HashIdsConfig;
import com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa.LinkEntity;
import com.github.alxsshv.shortlinkcreationservice.adapter.api.spring.webmvc.dto.ServiceMessage;
import com.github.alxsshv.shortlinkcreationservice.adapter.api.spring.webmvc.dto.ShortLinkRequest;
import com.github.alxsshv.shortlinkcreationservice.integration.AbstractIntegrationTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.client.RestTestClient;

class LinkControllerV1IntegrationTests extends AbstractIntegrationTest {

    @LocalServerPort
    private int port;

    private RestTestClient restClient;

    @Autowired
    private JdbcClient jdbcClient;

    @BeforeEach
    void setUpRestClient() {
        restClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + port + "/api/v1/links")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }


    @Test
    @DisplayName("Test generateShortLink when sent valid request without ttl then return valid token")
    void testGenerateShortLink_whenSentValidRequestWithoutTtl_thenReturnValidToken() {
        ShortLinkRequest request = new ShortLinkRequest("https://google.com", null);

        String linkToken = restClient.post()
                .body(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        LinkEntity linkEntity = jdbcClient.sql("SELECT * FROM links.links WHERE token = :token")
                .param("token", linkToken)
                .query(LinkEntity.class)
                .single();

        Assertions.assertNotNull(linkToken);
        Assertions.assertEquals(HashIdsConfig.HASHIDS_MIN_LENGTH, linkToken.length());
        Assertions.assertNotNull(linkEntity);
        Assertions.assertEquals(request.url(), linkEntity.getUrl());
        Assertions.assertNull(linkEntity.getExpiredAt());

    }

    @Test
    @DisplayName("Test generateShortLink when sent valid request with ttl then return valid token")
    void testGenerateShortLink_whenSentValidRequestWithTtl_thenReturnValidToken() {
        ShortLinkRequest request = new ShortLinkRequest("https://google.com", "PT10M");

        String linkToken = restClient.post()
                .body(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        LinkEntity linkEntity = jdbcClient.sql("SELECT * FROM links.links WHERE token = :token")
                .param("token", linkToken)
                .query(LinkEntity.class)
                .single();

        Assertions.assertNotNull(linkToken);
        Assertions.assertEquals(HashIdsConfig.HASHIDS_MIN_LENGTH, linkToken.length());
        Assertions.assertNotNull(linkEntity);
        Assertions.assertEquals(request.url(), linkEntity.getUrl());
        Assertions.assertNotNull(linkEntity.getExpiredAt());

    }

    @Test
    @DisplayName("Test generateShortLink when sent request with null url then return bad request")
    void testGenerateShortLink_whenRequestsUrlIsNull_thenReturnBadRequest() {
        ShortLinkRequest request = new ShortLinkRequest(null, "PT10M");

        restClient.post()
                .body(request)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ServiceMessage.class)
                .isEqualTo(new ServiceMessage("URL cannot be null or empty"));
    }

    @Test
    @DisplayName("Test generateShortLink when sent request with empty url then return bad request")
    void testGenerateShortLink_whenRequestUrlIsEmpty_thenReturnBadRequest() {
        ShortLinkRequest request = new ShortLinkRequest("", "PT10M");

        restClient.post()
                .body(request)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Test generateShortLink when sent request with url size less than 8 then return bad request")
    void testGenerateShortLink_whenRequestUrlSizeIsLessThan8_thenReturnBadRequest() {
        ShortLinkRequest request = new ShortLinkRequest("http://", "PT10M");

        restClient.post()
                .body(request)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Test generateShortLink when sent request with url size great than 255 then return bad request")
    void testGenerateShortLink_whenRequestUrlSizeIsGreatThan255_thenReturnBadRequest() {
        ShortLinkRequest request = new ShortLinkRequest(
                "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/" +
                        "spring-framework/docs/current/javadoc-api/org/springframework/spring-framework/docs/" +
                        "current/javadoc-api/org/springframework/spring-framework/docs/current/javadoc-api/" +
                        "org/JdbcClient.html",
                "PT10M");

        restClient.post()
                .body(request)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ServiceMessage.class)
                .isEqualTo(new ServiceMessage("URL must contain between 8 and 255 characters"));
    }

    @Test
    @DisplayName("Test generateShortLink when sent request with invalid url then return bad request")
    void testGenerateShortLink_whenRequestHasInvalidUrl_thenReturnBadRequest() {
        ShortLinkRequest request = new ShortLinkRequest("httpdasaddad", "PT10M");

        restClient.post()
                .body(request)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ServiceMessage.class)
                .isEqualTo(new ServiceMessage("Invalid URL"));
    }

    @Test
    @DisplayName("Test generateShortLink when sent request with invalid ttl format then return bad request")
    void testGenerateShortLink_whenRequestHasInvalidTtlFormat_thenReturnBadRequest() {
        ShortLinkRequest request = new ShortLinkRequest("http://example.com", "NoISO8601DurationFormat");

        restClient.post()
                .body(request)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ServiceMessage.class)
                .isEqualTo(new ServiceMessage("TTL string must be in duration format ISO 8601"));
    }

}
