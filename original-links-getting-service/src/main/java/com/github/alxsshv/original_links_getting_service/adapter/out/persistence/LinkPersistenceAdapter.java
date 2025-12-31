package com.github.alxsshv.original_links_getting_service.adapter.out.persistence;

import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import com.github.alxsshv.original_links_getting_service.application.port.out.FindLinkByTokenUseCase;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class LinkPersistenceAdapter implements FindLinkByTokenUseCase {

    private final LinkRepository linkRepository;

    private final LinkMapper linkMapper;


    @Override
    public Optional<Link> findLinkByToken(String token) {
        return linkRepository.findByToken(token).map(linkMapper::toDomain);
    }
}
