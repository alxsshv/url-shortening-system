package com.github.alxsshv.original_links_getting_service.application.domain.entity;

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