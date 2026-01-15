package com.github.alxsshv.original_links_getting_service.adapter.acl.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** Репозиторий сущности {@link LinkEntity} */
@Repository
public interface LinkRepository extends JpaRepository<LinkEntity, Long> {

    /** Метод поиска {@link LinkEntity} в БД по токену */
    Optional<LinkEntity> findByToken(String token);

}
