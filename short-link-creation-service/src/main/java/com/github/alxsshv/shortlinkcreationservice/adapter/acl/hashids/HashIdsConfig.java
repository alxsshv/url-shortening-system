package com.github.alxsshv.shortlinkcreationservice.adapter.acl.hashids;

import org.hashids.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**Конфигурация для библиотеки генерации уникальных токенов - коротких ссылок*/
@Configuration
public class HashIdsConfig {

    /** Соль для генерации токенов */
    public static final String HASHIDS_SALT = "link_generation_salt";
    /** Количество символов в токене */
    public static final int HASHIDS_MIN_LENGTH = 6;

    /**Бин для генерации токенов*/
    @Bean
    public Hashids hashids() {
        return new Hashids(HASHIDS_SALT,HASHIDS_MIN_LENGTH);
    }
    /**Адаптер Api приложения для взаимодействия с библиотекой Hashids*/
    @Bean
    public ShortLinkTokenGenerator linkTokenGenerator(Hashids hashids) {
        return new ShortLinkTokenGenerator(hashids);
    }

}
