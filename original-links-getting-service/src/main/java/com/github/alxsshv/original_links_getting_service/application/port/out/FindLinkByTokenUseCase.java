package com.github.alxsshv.original_links_getting_service.application.port.out;

import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;

import java.util.Optional;

@FunctionalInterface
public interface FindLinkByTokenUseCase {

    Optional<Link> findLinkByToken(String token);

}
