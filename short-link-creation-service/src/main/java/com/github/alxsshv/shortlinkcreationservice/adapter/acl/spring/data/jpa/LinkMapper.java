package com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa;

import com.github.alxsshv.shortlinkcreationservice.application.domain.model.Link;


/** Маппер сущности {@link LinkEntity} в доменную модель {@link Link} и обратно. */
public class LinkMapper {

    /** Метод маппинга доменной модели {@link Link} в сущность {@link LinkEntity}.
     * @param link - объект класа {@link Link}, которую необходимо преобразовать в сущность {@link LinkEntity}.
     * @return объект {@link LinkEntity} если аргумент link не равен null,
     * или возвращает null, если параметр link равен null.*/
    public LinkEntity toEntity(Link link) {
        if ( link == null ) {
            return null;
        }
        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setNew(true);
        linkEntity.setId(link.getId());
        linkEntity.setToken(link.getToken());
        linkEntity.setUrl(link.getUrl());
        linkEntity.setCreatedAt(link.getCreatedAt());
        linkEntity.setExpiredAt(link.getExpiredAt());
        return linkEntity;
    }

    /** Метод маппинга сущности в доменную модель.
     * @param linkEntity - сущность БД {@link LinkEntity}, которую необходимо преобразовать в {@link Link}.
     * @return объект {@link Link} если аргумент linkEntity не равен null,
     * или возвращает null, если linkEntity равна null.*/
    public Link toDomain(LinkEntity linkEntity) {
        if ( linkEntity == null ) {
            return null;
        }
        return Link.builder()
                .id(linkEntity.getId())
                .token(linkEntity.getToken())
                .url(linkEntity.getUrl())
                .createdAt(linkEntity.getCreatedAt())
                .expiredAt(linkEntity.getExpiredAt())
                .build();
    }
}
