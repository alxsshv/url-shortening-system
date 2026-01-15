package com.github.alxsshv.original_links_getting_service.application.api;

import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import com.github.alxsshv.original_links_getting_service.application.domain.exception.LinkNotFoundException;

/** Интерфейс описывающий Api слоя приложения для обработки запросов от внешних систем
 *  на получение оригинальной ссылки*/
@FunctionalInterface
public interface ForFindOriginalUrlApi {

    /**Метод поиска оригинальной ссылки по токену короткой ссылки.
     * @param token токен короткой ссылки.
     * @return возвращает объект {@link Link}, cодержащий оригинальную ссылку.
     * @throws LinkNotFoundException если ссылка не найдена */
    Link findOriginalLink(String token);

}
