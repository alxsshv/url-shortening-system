package com.github.alxsshv.shortlinkcreationservice.application.api;

import java.time.Duration;
import java.util.Optional;

/** Интерфейс описывающий Api слоя приложения для обработки запросов от внешних систем для генерации коротких ссылок */
public interface GenerateShortLinkApi {

    /** Метод для генерации короткой ссылки.
     * @param originalUrl оригинальная ссылка.
     * @param ttlOptional время жизни ссылки.
     * @return короткая ссылка в виде строки*/
    String generateShortLink(String originalUrl, Optional<Duration> ttlOptional);
}
