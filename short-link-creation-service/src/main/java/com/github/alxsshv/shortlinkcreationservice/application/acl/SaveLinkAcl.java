package com.github.alxsshv.shortlinkcreationservice.application.acl;


import com.github.alxsshv.shortlinkcreationservice.application.domain.model.Link;

/** Интерфейс, описывающий anti-corruption layer
 * (acl - буферный слой) между приложением и базой данных
 * в части сохранения сведений о ссылке.*/
@FunctionalInterface
public interface SaveLinkAcl {

    /** Сохраняет ссылку в базе данных.
     * @param link - ссылка, которую необходимо сохранить.
     * @return сохранённая ссылка в виде объекта {@link Link}.*/
    Link save(Link link);
}
