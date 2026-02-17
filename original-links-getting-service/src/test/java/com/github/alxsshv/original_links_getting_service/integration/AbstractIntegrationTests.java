package com.github.alxsshv.original_links_getting_service.integration;

import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;


@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractIntegrationTests {

    @Autowired
    protected CacheManager cacheManager;

    @Autowired
    protected JdbcClient jdbcClient;


    public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17")
            .withInitScripts("integration/init.sql", "integration/create_links_sequence.sql");

    public static final RedisContainer redis = new RedisContainer("redis:8.2.3");


    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.redis.host", redis::getHost);
        registry.add("spring.redis.port", redis::getFirstMappedPort);
    }

    @BeforeAll
    static void startContainers() {
        postgres.start();
        redis.start();
    }

    @AfterAll
    static void stopContainers() {
        postgres.stop();
        redis.stop();
    }
}
