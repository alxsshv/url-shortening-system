package com.github.alxsshv.shortlinkcreationservice.application.acl;

/** Интерфейс, описывающий anti-corruption layer
 * (acl - буферный слой) между приложением и базой данных в части удаления просроченных ссылок.*/
public interface DeleteExpiredLinksAcl {

    /** Метод удаления просроченных ссылок. */
    void deleteExpiredLinks();

}
