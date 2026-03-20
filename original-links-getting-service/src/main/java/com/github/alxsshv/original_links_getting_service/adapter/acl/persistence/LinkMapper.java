package com.github.alxsshv.original_links_getting_service.adapter.acl.persistence;

import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;

/** Класс маппера сущности {@link LinkEntity} в доменную модель {@link Link}. */
public class LinkMapper {

    /** Метод маппинга сущности в доменную модель.
     * @param linkEntity - сущность БД {@link LinkEntity}, которую необходимо преобразовать в {@link Link}.
     * @return объект {@link Link} если аргумент linkEntity не равен null,
     * или возвращает null, если linkEntity равна null.*/
    public Link toDomain(LinkEntity linkEntity) {
        if ( linkEntity == null ) {
            return null;
        }
        Link.LinkBuilder link = Link.builder();
        link.id(linkEntity.getId());
        link.url(linkEntity.getUrl());
        link.token(linkEntity.getToken());
        link.createdAt(linkEntity.getCreatedAt());
        link.expiredAt(linkEntity.getExpiredAt());
        return link.build();
    }
}
