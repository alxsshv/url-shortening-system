package com.github.alxsshv.shortlinkcreationservice.application.acl;

/** Интерфейс, описывающий anti-corruption layer
 * (acl - буферный слой) между приложением и базой данных
 * в части получения идентификатора для создаваемой короткой ссылки.*/
public interface GetNextIdAcl {

    /** Получение идентификатора из базы данных для новой ссылки. */
    long getNextId();

}
