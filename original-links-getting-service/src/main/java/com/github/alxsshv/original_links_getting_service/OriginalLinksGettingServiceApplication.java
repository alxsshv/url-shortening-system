package com.github.alxsshv.original_links_getting_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class OriginalLinksGettingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OriginalLinksGettingServiceApplication.class, args);
	}

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeHeaders(true); // Включает логирование всех заголовков
        filter.setIncludeQueryString(true);
        return filter;
    }

}
