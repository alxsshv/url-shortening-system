package com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.time.Instant;
/** Сущность для хранения объектов класса
 * {@link com.github.alxsshv.shortlinkcreationservice.application.domain.model.Link} в БД */
@Getter
@Setter
@Entity
@Table(name = "links", schema = "links")
@NoArgsConstructor
@AllArgsConstructor
public class LinkEntity implements Persistable<Long> {

    /** Свойство для определения, является ли объект новым (для Hibernate),
     *  необходимо т.к. ID присваивается на стороне приложения*/
    @Transient
    private boolean isNew = true;

    /** Идентификатор */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /** Токен, являющийся уникальной частью короткой ссылки, предназначенный для получения оригинальной ссылки */
    @Column(name = "token", columnDefinition = "VARCHAR(10)")
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