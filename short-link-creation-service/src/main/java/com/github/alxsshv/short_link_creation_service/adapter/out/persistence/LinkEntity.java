package com.github.alxsshv.short_link_creation_service.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "links", schema = "links")
@NoArgsConstructor
@AllArgsConstructor
public class LinkEntity implements Persistable<Long> {

    @Transient
    private boolean isNew = true;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token", columnDefinition = "VARCHAR(10)")
    private String token;

    @Column(name = "url", columnDefinition = " VARCHAR(255)", nullable = false)
    private String url;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private Instant createdAt;

    @Column(name = "expired_at", columnDefinition = "TIMESTAMP")
    private Instant expiredAt;

}