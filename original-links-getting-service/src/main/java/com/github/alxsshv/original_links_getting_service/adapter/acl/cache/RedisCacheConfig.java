package com.github.alxsshv.original_links_getting_service.adapter.acl.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/** Конфигурация Redis кэша */
@Configuration
@EnableCaching
public class RedisCacheConfig implements CachingConfigurer {

    /** Адрес распределённого кэша (Redis - инстанса) */
    @Value("${spring.redis.host}")
    private String redisHost;

    /** Порт для подключения к Redis */
    @Value("${spring.redis.port}")
    private int redisPort;

    /** Фабрика подключений к Redis c использованием jedis. */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    }

    /** Объект управляющий кэшированием данных в Redis */
    @Bean
    public RedisCacheManager cacheManager(RedisCacheConfiguration linkRedisCacheConfiguration) {
        return RedisCacheManager.builder(jedisConnectionFactory())
                .cacheDefaults(linkRedisCacheConfiguration)
                .build();
    }

    /** Конфигурация Redis кэша с вычисляемым значением TTL */
    @Bean
    public RedisCacheConfiguration linkRedisCacheConfiguration(LinkCacheTtlFunction linkCacheTtlFunction) {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(linkCacheTtlFunction);
    }

    /** Функция вычисления TTL для кэша */
    @Bean
    public LinkCacheTtlFunction linkCacheTtlFunction() {
        return new LinkCacheTtlFunction();
    }
}
