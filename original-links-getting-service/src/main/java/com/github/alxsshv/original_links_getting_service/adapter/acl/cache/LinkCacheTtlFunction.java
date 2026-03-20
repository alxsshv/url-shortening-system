package com.github.alxsshv.original_links_getting_service.adapter.acl.cache;

import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.time.Instant;

/** Функция для вычисления TTL кэша для сущности Link*/
public class LinkCacheTtlFunction implements RedisCacheWriter.TtlFunction {

    @Value("${spring.cache.redis.time-to-live}")
    private Duration defaultTtl;

    /** Метод определяет TTL кэша.
     * @param key - ключ кэша,
     * @param value - объект, сохраняемый по указанному ключу.
     * @return если объект является экземпляром Link значение ttl вычисляется на основе поля expiredAt,
     * иначе возвращается значение ttl, определенное в application.yml */
    @Override
    public Duration getTimeToLive(Object key, @Nullable Object value) {
        if (value instanceof Link link && link.getExpiredAt() != null) {
            return calculateLinkTtl(link.getExpiredAt());
        }
        return defaultTtl;
    }
    /** Методы вычисляет TTL кэша для сущности Link на основе значения поля expiredAt
     * @param expiredAt значение поля expiredAt сущности Link
     * @return возвращает TTL в формате Duration. Если время жизни ссылки истекло, то возвращает нулевую длительность*/
    private Duration calculateLinkTtl(Instant expiredAt) {
        Instant now = Instant.now();
        if (expiredAt.isAfter(now)) {
            long ttlMillis = expiredAt.toEpochMilli() - now.toEpochMilli();
            return Duration.ofMillis(ttlMillis);
        }
        return Duration.ZERO;
    }
}
