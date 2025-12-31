package com.github.alxsshv.short_link_creation_service.configuration;

import com.github.alxsshv.short_link_creation_service.adapter.out.util.ShortLinkTokenGenerator;
import org.hashids.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class UtilsBeans {

    private static final String HASHIDS_SALT = "link_generation_salt";
    private static final int HASHIDS_MIN_LENGTH = 6;

    @Bean
    public Hashids hashids() {
        return new Hashids(HASHIDS_SALT,HASHIDS_MIN_LENGTH);
    }

    @Bean
    public ShortLinkTokenGenerator linkTokenGenerator(Hashids hashids) {
        return new ShortLinkTokenGenerator(hashids);
    }


    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }
}
