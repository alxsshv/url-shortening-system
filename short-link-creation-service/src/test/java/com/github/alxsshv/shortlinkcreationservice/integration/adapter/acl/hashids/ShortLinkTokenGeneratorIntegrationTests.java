package com.github.alxsshv.shortlinkcreationservice.integration.adapter.acl.hashids;

import com.github.alxsshv.shortlinkcreationservice.adapter.acl.hashids.HashIdsConfig;
import com.github.alxsshv.shortlinkcreationservice.adapter.acl.hashids.ShortLinkTokenGenerator;
import com.github.alxsshv.shortlinkcreationservice.integration.AbstractIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ShortLinkTokenGeneratorIntegrationTests extends AbstractIntegrationTest {

    @Autowired
    private ShortLinkTokenGenerator shortLinkTokenGenerator;

    @Test
    @DisplayName("Test generate method when set valid id then return valid short link token")
    void testGenerate_whenSetValidId_thenReturnValidShortLinkToken() {
        int expectedTokenLength = HashIdsConfig.HASHIDS_MIN_LENGTH;
        String token = shortLinkTokenGenerator.generate(1L);

        Assertions.assertNotNull(token);
        Assertions.assertEquals(expectedTokenLength, token.length());
    }

}
