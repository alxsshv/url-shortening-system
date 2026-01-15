package com.github.alxsshv.shortlinkcreationservice.application.acl;

/** Интерфейс, описывающий anti-corruption layer
 * (acl - буферный слой) между приложением и библиотекой Hashids.*/
public interface GenerateShortLinkTokenAcl {

    /** Генерирует короткую ссылку на основе идентификатора ссылки.
     * @param id - идентификатор ссылки.
     * @return возвращает короткую ссылку.*/
    String generate(Long id);

}
