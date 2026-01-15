package com.github.alxsshv.shortlinkcreationservice.unit.adapter.acl.hashids;

import com.github.alxsshv.shortlinkcreationservice.adapter.acl.hashids.ShortLinkTokenGenerator;
import org.hashids.Hashids;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShortLinkTokenGeneratorTest {

    @Mock
    private Hashids hashids;

    @InjectMocks
    private ShortLinkTokenGenerator shortLinkTokenGenerator;

    @Test
    void testGenerateMethod_whenIdIsValid_thenReturnValidToken() {
        Long id = 1L;
        String expectedString = "token";
        when(hashids.encode(id)).thenReturn(expectedString);

        String actualString = shortLinkTokenGenerator.generate(id);

        Assertions.assertNotNull(actualString);
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    void testGenerateMethod_whenIdIsNull_thenThrowIllegalArgumentException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> shortLinkTokenGenerator.generate(null));
    }

    @Test
    void testGenerateMethod_whenIdIsNegative_thenThrowIllegalArgumentException() {
        Long id = -1L;
        Assertions.assertThrows(IllegalArgumentException.class, () -> shortLinkTokenGenerator.generate(id));

    }
}
