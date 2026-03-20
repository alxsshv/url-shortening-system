package com.github.alxsshv.shortlinkcreationservice.adapter.acl.hashids;

import com.github.alxsshv.shortlinkcreationservice.application.acl.GenerateShortLinkTokenAcl;
import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;

/** Класс, реализующий буферный слой для взаимодействия приложения с библиотекой Hashids */
@RequiredArgsConstructor
public class ShortLinkTokenGenerator implements GenerateShortLinkTokenAcl {

    /** Объект библиотеки Hashids, генерирующий короткие буквенно-числовые токены */
    private final Hashids hashids;

    /** Метод, генерирующий короткий буквенно-числовой токен */
    public String generate(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException(String.format("Id for token generation cannot be null or negative. Received id = [%s]", id));
        }
        return hashids.encode(id);
    }

}
