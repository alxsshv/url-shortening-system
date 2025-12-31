package com.github.alxsshv.short_link_creation_service.adapter.in.rest;

import com.github.alxsshv.short_link_creation_service.adapter.in.rest.dto.ShortLinkRequest;
import com.github.alxsshv.short_link_creation_service.application.port.in.GenerateShortLinkApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/links")
public class LinkControllerV1 {

    private final GenerateShortLinkApi generateShortLinkUseCase;

    @PostMapping()
    public ResponseEntity<String> generateShortLink(@RequestBody @Valid ShortLinkRequest request) {
        Optional<Duration> ttlOpt = Optional.ofNullable(request.ttl()).map(Duration::parse);
        String shotLink = generateShortLinkUseCase.generateShortLink(request.url(), ttlOpt);
        return ResponseEntity.ok(shotLink);
    }

}
