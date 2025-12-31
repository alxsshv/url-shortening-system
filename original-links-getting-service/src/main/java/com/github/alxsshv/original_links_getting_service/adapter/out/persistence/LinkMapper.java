package com.github.alxsshv.original_links_getting_service.adapter.out.persistence;

import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LinkMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "url", source = "url")
    Link toDomain(LinkEntity linkEntity);
}
