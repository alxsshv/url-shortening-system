package com.github.alxsshv.short_link_creation_service.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<LinkEntity, Long> {

    @Query(value = "SELECT nextval('links_seq')", nativeQuery = true)
    long getNextId();

}
