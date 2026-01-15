package com.github.alxsshv.shortlinkcreationservice.application.domain.model;

import lombok.*;

import java.time.Instant;
import java.util.Objects;

/** Объект (доменная модель), хранящий информацию о ссылке */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Link {

    /** Идентификатор */
    private Long id;

    /** Токен, являющийся уникальной частью короткой ссылки, предназначенный для получения оригинальной ссылки */
    private String token;

    /** Оригинальная ссылка */
    private String url;

    /** Дата создания */
    private Instant createdAt;

    /** Срок действия */
    private Instant expiredAt;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return Objects.equals(id, link.id) && Objects.equals(token, link.token) && Objects.equals(url, link.url) && Objects.equals(createdAt, link.createdAt) && Objects.equals(expiredAt, link.expiredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, url, createdAt, expiredAt);
    }
}