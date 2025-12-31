package com.github.alxsshv.original_links_getting_service.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "links", schema = "links")
@NoArgsConstructor
@AllArgsConstructor
public class LinkEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token", columnDefinition = "VARCHAR(10)", nullable = false)
    private String token;

    @Column(name = "url", columnDefinition = " VARCHAR(255)", nullable = false)
    private String url;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private Instant createdAt;

    @Column(name = "expired_at", columnDefinition = "TIMESTAMP")
    private Instant expiredAt;

}