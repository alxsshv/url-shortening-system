package com.github.alxsshv.original_links_getting_service.adapter.acl.persistence;

import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import com.github.alxsshv.original_links_getting_service.application.acl.ForGettingUrlAcl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

/** Адаптер для взаимодействия БД с приложением, реализует anti-corruption layer
 * (acl - буферный слой между приложением и внешними системами)*/
@RequiredArgsConstructor
public class LinkPersistenceAdapter implements ForGettingUrlAcl {

    /** Репозиторий для работы с БД */
    private final LinkRepository linkRepository;

    /** Маппер для преобразования сущностей БД в сущности домена */
    private final LinkMapper linkMapper;

    /** Метод для поиска объекта {@link Link} по токену в БД
     * Если значение найдено в БД - результат кэшируется.
     * @param token - уникальный токен, являющийся частью короткой ссылки.
     * @return возвращает объект класса {@link Link}, если объект найден в БД, в противном случае возвращает null.*/
    @Cacheable(value = "links", unless = "#result == null")
    @Override
    public Link findLinkByToken(String token) {
        Optional<LinkEntity> linkEntityOpt = linkRepository.findByToken(token);
        return linkEntityOpt.map(linkMapper::toDomain).orElse(null);
    }
}
