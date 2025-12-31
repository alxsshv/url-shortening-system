package com.github.alxsshv.short_link_creation_service.application.domain.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Link {

    private Long id;

    private String token;

    private String url;

    private Instant createdAt;

    private Instant expiredAt;

}