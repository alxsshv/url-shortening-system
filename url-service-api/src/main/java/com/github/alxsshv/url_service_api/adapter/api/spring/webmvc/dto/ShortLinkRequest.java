package com.github.alxsshv.url_service_api.adapter.api.spring.webmvc.dto;



import com.github.alxsshv.url_service_api.adapter.api.spring.webmvc.validation.IsValidDurationString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

/** DTO для запроса на создание короткой ссылки */
public record ShortLinkRequest (

        /** url - оригинальная ссылка.
         * Не может быть null или пустой. Должна быть длиной от 8 до 255 символов.*/
        @NotBlank(message = "URL cannot be null or empty")
        @URL(message = "Invalid URL")
        @Size(min=8, max = 255, message = "URL must contain between 8 and 255 characters")
        String url,

        /** ttl - время жизни ссылки в секундах (опциональный параметр), если не указан - ссылка не имеет срока давности. */
        @IsValidDurationString
        String ttl
) {}
