package com.github.alxsshv.url_service_api.application.api;

/** Интерфейс описывающий Api слоя приложения для получения запросов от внешних систем для получения оригинальной ссылки */
public interface GetOriginalUrlApi {

    /** Метод для получения оригинальной ссылки.
     * @param shortUrl - токен (уникальная часть) короткой ссылки.
     * @return возвращает оригинальную ссылку в виде строки. */
    String getOriginalUrl(String shortUrl);
}
