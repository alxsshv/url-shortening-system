package com.github.alxsshv.original_links_getting_service.application.acl;

import com.github.alxsshv.original_links_getting_service.application.domain.entity.Link;

/** Интерфейс, описывающий anti-corruption layer
 * (acl - буферный слой) между приложением и базой данных.*/
@FunctionalInterface
public interface ForGettingUrlAcl {

    /** Метод для поиска объекта {@link Link} по токену в БД
     * @param token - уникальный токен, являющийся частью короткой ссылки.
     * @return возвращает объект класса {@link Link}, если объект найден в БД, в противном случае возвращает null.*/
    Link findLinkByToken(String token);

}
