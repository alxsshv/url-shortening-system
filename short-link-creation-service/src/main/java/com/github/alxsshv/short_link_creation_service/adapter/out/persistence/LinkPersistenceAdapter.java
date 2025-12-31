package com.github.alxsshv.short_link_creation_service.adapter.out.persistence;

import com.github.alxsshv.short_link_creation_service.application.domain.model.Link;
import com.github.alxsshv.short_link_creation_service.application.port.out.persistence.GetNextIdUseCase;
import com.github.alxsshv.short_link_creation_service.application.port.out.persistence.SaveLinkUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class LinkPersistenceAdapter implements SaveLinkUseCase, GetNextIdUseCase {

    private final LinkRepository linkRepository;

    private final LinkMapper linkMapper;

    @Transactional
    @Override
    public Link save(Link link) {
        LinkEntity linkEntity = linkRepository.save(linkMapper.toEntity(link));
        return linkMapper.toDomain(linkEntity);
    }

    @Override
    public long getNextId() {
        return linkRepository.getNextId();
    }
}
