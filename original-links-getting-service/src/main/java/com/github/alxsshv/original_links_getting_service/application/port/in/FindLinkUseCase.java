package com.github.alxsshv.original_links_getting_service.application.port.in;

import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;
import com.github.alxsshv.original_links_getting_service.application.domain.exception.LinkNotFoundException;

@FunctionalInterface
public interface FindLinkUseCase {

    /**Метод поиска длинной ссылки по токену короткой ссылки.
     * @param token токен короткой ссылки.
     * @return исходную длинную ссылку в виде строки.
     * @throws LinkNotFoundException если ссылка не найдена,
     * @throws IllegalArgumentException если token имеет неверный формат*/
    Link findLongLink(String token);

}
