package com.github.alxsshv.url_service_api.adapter.api.spring.webmvc;

import com.github.alxsshv.url_service_api.adapter.api.spring.webmvc.dto.ShortLinkRequest;
import com.github.alxsshv.url_service_api.application.port.api.GetOriginalUrlApi;
import com.github.alxsshv.url_service_api.application.port.api.GetShortLinkApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/v1/link")
@RequiredArgsConstructor
@Validated
@Slf4j
public class LinkControllerV1 {

    private final GetOriginalUrlApi originalUrlApi;

    private final GetShortLinkApi shortLinkApi;

    @PostMapping
    public ResponseEntity<String> createShortLink(@RequestBody @Valid ShortLinkRequest request) {
        log.info("Request for creating short link");
        String url = shortLinkApi.getShortLink(request.url(), request.ttl());
        log.info("Short link created: {}", url);
        return ResponseEntity.ok(url);
    }


    @GetMapping("/{shortLink}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortLink) {
        log.info("Getting original url for short link: {}", shortLink);
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .body(originalUrlApi.getOriginalUrl(shortLink));
    }

    @GetMapping("/redirect/{shortLink}")
    public RedirectView redirectOriginalUrl(@PathVariable String shortLink) {
        log.info("Redirect to original url by short link: {}", shortLink);
        return new RedirectView(originalUrlApi.getOriginalUrl(shortLink));
    }


}
