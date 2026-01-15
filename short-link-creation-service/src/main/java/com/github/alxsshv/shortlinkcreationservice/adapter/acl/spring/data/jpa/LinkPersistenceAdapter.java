package com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa;

import com.github.alxsshv.shortlinkcreationservice.application.acl.DeleteExpiredLinksAcl;
import com.github.alxsshv.shortlinkcreationservice.application.acl.GetNextIdAcl;
import com.github.alxsshv.shortlinkcreationservice.application.acl.SaveLinkAcl;
import com.github.alxsshv.shortlinkcreationservice.application.domain.model.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/** Класс, реализующий буферный слой для взаимодействия приложения с базой данных */
@RequiredArgsConstructor
public class LinkPersistenceAdapter implements SaveLinkAcl, GetNextIdAcl, DeleteExpiredLinksAcl {

    /** Репозиторий для взаимодействия с базой данных */
    private final LinkRepository linkRepository;

    /** Маппер для преобразования сущности {@link LinkEntity} в доменный объект {@link Link} */
    private final LinkMapper linkMapper;

    /** Метод для сохранения ссылки в базу данных */
    @Transactional
    @Override
    public Link save(Link link) {
        LinkEntity linkEntity = linkRepository.save(linkMapper.toEntity(link));
        return linkMapper.toDomain(linkEntity);
    }

    /** Метод для получения следующего идентификатора для создаваемой ссылки */
    @Transactional
    @Override
    public long getNextId() {
        return linkRepository.getNextId();
    }

    /** Метод для удаления просроченных ссылок */
    @Transactional
    @Override
    public void deleteExpiredLinks() {
        linkRepository.deleteAllByExpiredAtBefore(Instant.now());
    }
}
