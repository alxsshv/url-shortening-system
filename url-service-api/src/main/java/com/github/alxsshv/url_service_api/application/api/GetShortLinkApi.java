package com.github.alxsshv.url_service_api.application.api;

/** Интерфейс описывающий Api слоя приложения для обработки запросов от внешних систем для получения коротких ссылок */
public interface GetShortLinkApi {

    /** Метод для генерации короткой ссылки.
     * @param originalUrl оригинальная ссылка.
     * @param ttl время жизни ссылки.
     * @return короткая ссылка в виде строки*/
    String getShortLink(String originalUrl, String ttl);

}
