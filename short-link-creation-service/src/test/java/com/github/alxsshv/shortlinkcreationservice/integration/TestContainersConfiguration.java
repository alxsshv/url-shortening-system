package com.github.alxsshv.shortlinkcreationservice.integration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.postgresql.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainersConfiguration {

    @Bean
    PostgreSQLContainer postgreSqlContainer() {
        return new PostgreSQLContainer("postgres:17")
                .withInitScripts("integration/init_schema.sql", "integration/create_links_sequence.sql");
    }
}
