package com.github.alxsshv.shortlinkcreationservice.adapter.acl.spring.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;

/** Репозиторий для сущности {@link LinkEntity} */
@Repository
public interface LinkRepository extends JpaRepository<LinkEntity, Long> {

    /** Метод получения следующего идентификатора для ссылки */
    @Query(value = "SELECT nextval('links.links_seq')", nativeQuery = true)
    long getNextId();

    /** Метод удаления всех ссылок, срок действия которых истек */
    void deleteAllByExpiredAtBefore(Instant expiredAt);
}
