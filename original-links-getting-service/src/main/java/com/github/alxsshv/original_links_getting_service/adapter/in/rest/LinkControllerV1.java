package com.github.alxsshv.original_links_getting_service.adapter.in.rest;

import com.github.alxsshv.original_links_getting_service.adapter.in.rest.dto.Url;
import com.github.alxsshv.original_links_getting_service.application.port.in.FindLinkUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/links")
@RequiredArgsConstructor
@Validated
@Slf4j
public class LinkControllerV1 {

    private final FindLinkUseCase findLinkUseCase;

    @RequestMapping("/{token}")
    public ResponseEntity<Url> getLink(@PathVariable String token) {
        if (!StringUtils.hasText(token)) {
            throw new IllegalArgumentException("Token has an incorrect format");
        }
        log.info("Received request for token: {}", token);
        return ResponseEntity.ok(new Url(findLinkUseCase.findLongLink(token).getUrl()));
    }

}


