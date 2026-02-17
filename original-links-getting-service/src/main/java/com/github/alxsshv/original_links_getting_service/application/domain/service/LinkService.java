package com.github.alxsshv.original_links_getting_service.application.domain.service;

import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import com.github.alxsshv.original_links_getting_service.application.domain.exception.LinkNotFoundException;
import com.github.alxsshv.original_links_getting_service.application.api.ForFindOriginalUrlApi;
import com.github.alxsshv.original_links_getting_service.application.acl.ForGettingUrlAcl;
import lombok.RequiredArgsConstructor;

/** Сервис, реализующий Api приложения для получения запросов от внешних систем */
@RequiredArgsConstructor
public class LinkService implements ForFindOriginalUrlApi {

    /** Реализация Интерфейса, описывающий буферный слой между приложением и базой данных.*/
    private final ForGettingUrlAcl forGettingUrlAcl;

    /**Метод поиска оригинальной ссылки по токену короткой ссылки.
     * @param token токен короткой ссылки.
     * @return возвращает объект {@link Link}, cодержащий оригинальную ссылку.
     * @throws LinkNotFoundException если ссылка не найдена. */
    @Override
    public Link findOriginalLink(String token) {
        Link link = forGettingUrlAcl.findLinkByToken(token);
        if (link != null) {
            return link;
        }
        throw new LinkNotFoundException("Link with token [ %s ] not found", token);
    }
}
