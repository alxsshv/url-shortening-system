package com.github.alxsshv.shortlinkcreationservice.integration;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.testcontainers.postgresql.PostgreSQLContainer;


@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainersConfiguration.class)
public class AbstractIntegrationTest {

    @Autowired
    protected JdbcClient jdbcClient;

    @Autowired
    protected PostgreSQLContainer postgreSQLContainer;


    @AfterEach
    void clearDatabase() {
        JdbcTestUtils.deleteFromTables(jdbcClient, "links.links");
        jdbcClient.sql("ALTER SEQUENCE links.links_seq RESTART WITH 1").update();
    }






}
