package com.github.alxsshv.original_links_getting_service.application.domain.service;

import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import com.github.alxsshv.original_links_getting_service.application.domain.exception.LinkNotFoundException;
import com.github.alxsshv.original_links_getting_service.application.port.in.FindLinkUseCase;
import com.github.alxsshv.original_links_getting_service.application.port.out.FindLinkByTokenUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LinkService implements FindLinkUseCase {

    private final FindLinkByTokenUseCase findLinkByTokenUseCase;

    @Override
    public Link findLongLink(String token) {
        return findLinkByTokenUseCase.findLinkByToken(token)
                .orElseThrow(() -> new LinkNotFoundException("Link with token [ %s ] not found", token));
    }
}
