package com.github.alxsshv.shortlinkcreationservice.application.domain.service;


import com.github.alxsshv.shortlinkcreationservice.application.acl.DeleteExpiredLinksAcl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

/** Сервис для удаления устаревших ссылок */
@Slf4j
@RequiredArgsConstructor
public class ExpiredLinkCleanService {

    /** Буферный слой для взаимодействия с БД при удалении устаревших ссылок */
    private final DeleteExpiredLinksAcl deleteExpiredLinksAcl;

    /** Метод удаления устаревших ссылок в соответствии.
     * Запускается в отдельном потоке планировщиком с длительностью, определённой
     * свойством app.clean-expired-links-delay в файле application.yml */
    @Async
    @Scheduled(fixedDelayString = "${app.clean-expired-links-delay}")
    @Transactional
    public void deleteExpiredLinks() {
        log.info("Deleting expired links");
        deleteExpiredLinksAcl.deleteExpiredLinks();
    }

}
