package com.github.alxsshv.short_link_creation_service.application.port.out.persistence;

import com.github.alxsshv.short_link_creation_service.application.domain.model.Link;

@FunctionalInterface
public interface SaveLinkUseCase {

    Link save(Link link);
}
