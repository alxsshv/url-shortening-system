package com.github.alxsshv.original_links_getting_service.adapter.acl.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/** Сущность для хранения объектов класса
 * {@link com.github.alxsshv.original_links_getting_service.application.domain.entity.Link} в БД */
@Getter
@Setter
@Entity
@Table(name = "links", schema = "links")
@NoArgsConstructor
@AllArgsConstructor
public class LinkEntity {

    /** Идентификатор */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /** Токен, являющийся уникальной частью короткой ссылки, предназначенный для получения оригинальной ссылки */
    @Column(name = "token", columnDefinition = "VARCHAR(10)", nullable = false)
    private String token;

    /** Оригинальная ссылка */
    @Column(name = "url", columnDefinition = " VARCHAR(255)", nullable = false)
    private String url;

    /** Дата создания */
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private Instant createdAt;

    /** Срок действия */
    @Column(name = "expired_at", columnDefinition = "TIMESTAMP")
    private Instant expiredAt;

}