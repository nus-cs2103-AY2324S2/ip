package utils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void parseDateTime_success() {
        assertEquals(StringUtils.parseDateTime("24-05-2018 1900").toString(), "2018-05-24T19:00");
        assertEquals(StringUtils.parseDateTime("30-05-2024 0100").toString(), "2024-05-30T01:00");
        assertEquals(StringUtils.parseDateTime("24-05-2018").toString(), "2018-05-24T00:00");
    }

    @Test
    public void formatDateTime_success() {
        LocalDateTime dateTime = LocalDateTime.of(2018, 5, 24, 19, 0);
        assertEquals(StringUtils.formatDateTime(dateTime), "24-05-2018 1900");
    }

    @Test
    public void parseDateTime_error_invalidFormat() {
        assertThrows(java.time.format.DateTimeParseException.class, () -> {
            StringUtils.parseDateTime("24-May-2018 1900");
        });
    }

    @Test
    public void parseDateTime_error_nullInput() {
        assertThrows(java.lang.NullPointerException.class, () -> {
            StringUtils.parseDateTime(null);
        });
    }
}