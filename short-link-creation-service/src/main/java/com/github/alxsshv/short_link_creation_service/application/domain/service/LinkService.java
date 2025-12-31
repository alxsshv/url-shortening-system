package com.github.alxsshv.short_link_creation_service.application.domain.service;

import com.github.alxsshv.short_link_creation_service.adapter.out.util.ShortLinkTokenGenerator;
import com.github.alxsshv.short_link_creation_service.application.domain.model.Link;
import com.github.alxsshv.short_link_creation_service.application.port.in.GenerateShortLinkApi;
import com.github.alxsshv.short_link_creation_service.application.port.out.persistence.GetNextIdUseCase;
import com.github.alxsshv.short_link_creation_service.application.port.out.persistence.SaveLinkUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
public class LinkService implements GenerateShortLinkApi {

    private final GetNextIdUseCase getNextIdUseCase;

    private final SaveLinkUseCase saveLinkUseCase;

    private final ShortLinkTokenGenerator tokenGenerator;

    @Override
    @Transactional
    public String generateShortLink(String originalUrl, Optional<Duration> ttlOpt) {
        final long linkId = getNextIdUseCase.getNextId();
        final Instant expiredAt = ttlOpt.map(duration -> Instant.now().plus(duration)).orElse(null);
        Link link = Link.builder()
                .id(linkId)
                .url(originalUrl)
                .token(tokenGenerator.generate(linkId))
                .createdAt(Instant.now())
                .expiredAt(expiredAt)
                .build();
        return saveLinkUseCase.save(link).getToken();
    }
}
