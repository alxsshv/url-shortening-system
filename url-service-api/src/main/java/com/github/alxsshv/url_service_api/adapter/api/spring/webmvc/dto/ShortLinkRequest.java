package com.github.alxsshv.url_service_api.adapter.api.spring.webmvc.dto;



import com.github.alxsshv.url_service_api.adapter.api.spring.webmvc.validation.IsValidDurationString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record ShortLinkRequest (

        @NotBlank(message = "URL cannot be null or empty")
        @URL(message = "Invalid URL")
        @Size(min=6, max = 255, message = "URL must contain between 6 and 255 characters")
        String url,

        @IsValidDurationString
        String ttl
) {}
