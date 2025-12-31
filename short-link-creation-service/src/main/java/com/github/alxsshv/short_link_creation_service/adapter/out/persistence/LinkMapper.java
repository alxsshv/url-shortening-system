package com.github.alxsshv.short_link_creation_service.adapter.out.persistence;

import com.github.alxsshv.short_link_creation_service.application.domain.model.Link;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LinkMapper {

    LinkEntity toEntity(Link link);

    Link toDomain(LinkEntity linkEntity);
}
