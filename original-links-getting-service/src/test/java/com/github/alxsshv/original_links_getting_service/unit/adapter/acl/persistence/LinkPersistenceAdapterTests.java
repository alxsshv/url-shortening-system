package com.github.alxsshv.original_links_getting_service.unit.adapter.acl.persistence;

import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkEntity;
import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkMapper;
import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkPersistenceAdapter;
import com.github.alxsshv.original_links_getting_service.adapter.acl.persistence.LinkRepository;
import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LinkPersistenceAdapterTests {

    @Mock
    private LinkRepository linkRepository;

    @Mock
    private LinkMapper linkMapper;

    @InjectMocks
    private LinkPersistenceAdapter linkPersistenceAdapter;

    @Test
    @DisplayName("Test findLinkByToken when link is found then return valid link")
    void testFindLinkByToken_whenLinkIsFound_thenReturnValidLink() {
        String token = "token";

        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setId(4L);
        linkEntity.setToken("token");
        linkEntity.setUrl("http://url.com");
        linkEntity.setCreatedAt(Instant.now());
        linkEntity.setExpiredAt(Instant.now().plus(10, ChronoUnit.DAYS));

        Link expectedlink = Link.builder()
                .id(linkEntity.getId())
                .token(linkEntity.getToken())
                .url(linkEntity.getUrl())
                .createdAt(linkEntity.getCreatedAt())
                .expiredAt(linkEntity.getExpiredAt())
                .build();

        when(linkRepository.findByToken(token)).thenReturn(Optional.of(linkEntity));
        when(linkMapper.toDomain(linkEntity)).thenReturn(expectedlink);

        Link actualLink = linkPersistenceAdapter.findLinkByToken(token);

        Assertions.assertEquals(expectedlink, actualLink);
    }

    @Test
    @DisplayName("Test findLinkByToken when link is not found then return null")
    void testFindLinkByToken_whenLinkIsNotFound_thenReturnNull() {
        String token = "notFound";

        when(linkRepository.findByToken(token)).thenReturn(Optional.empty());

        Assertions.assertNull(linkPersistenceAdapter.findLinkByToken(token));
    }

}
