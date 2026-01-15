package com.github.alxsshv.original_links_getting_service.integration.adapter.api.spring.webmvc;

import com.github.alxsshv.original_links_getting_service.adapter.api.spring.webmvc.dto.ServiceMessage;
import com.github.alxsshv.original_links_getting_service.adapter.api.spring.webmvc.dto.Url;
import com.github.alxsshv.original_links_getting_service.integration.AbstractIntegrationTests;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LinkControllerV1IntegrationTests extends AbstractIntegrationTests {

    @LocalServerPort
    private int port;

    private static final String BASE_URL = "/api/v1/links";

    private RestTestClient restTestClient;

    @BeforeEach
    void setUpRestTestClient() {
        restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:" + port + BASE_URL)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    @AfterEach
    void clearCacheAndDatabase() {
        cacheManager.getCache("link").clear();
        JdbcTestUtils.deleteFromTables(jdbcClient, "links.links");
    }



    @Test
    @Sql("/integration/fill_database.sql")
    @DisplayName("Test getlink method when token is found then return valid link")
    void getLinkTest_whenTokenIsFound_thenReturnValidLink() {
        String shortLinkToken = "124alx";
        String expectedUrl = "https://start.spring.io/";

        restTestClient.get()
                .uri("/" + shortLinkToken)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Url.class)
                .isEqualTo(new Url(expectedUrl));
    }

    @Test
    @Sql("/integration/fill_database.sql")
    void getLinkTest_whenTokenIsNotFound_thenReturnNotFound() {
        String shortLinkToken = "notFoundToken";
        String expectedMessage = "Link with token [ notFoundToken ] not found";

        restTestClient.get()
                .uri("/" + shortLinkToken)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ServiceMessage.class)
                .isEqualTo(new ServiceMessage(expectedMessage));
    }

    @Test
    @Sql("/integration/fill_database.sql")
    @DisplayName("Test getlink method when token is whitespace then return BadRequest")
    void getLinkTest_whenTokenIsWhiteSpace_thenReturnBadRequest() {
        String shortLinkToken = " ";
        String expectedMessage = "Token has an incorrect format";

        restTestClient.get()
                .uri("/" + shortLinkToken)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(ServiceMessage.class)
                .isEqualTo(new ServiceMessage(expectedMessage));
    }

}
