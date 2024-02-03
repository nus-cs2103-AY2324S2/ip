package dylanbot;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void convertStringToDateTime_validDate_success() {
        // converting String with date results in correct DateTime format, with midnight as the time
        assertEquals(LocalDateTime.of(2024,2,1,0,0,0),
                Storage.convertStringToDateTime("2024-02-01"));

        // converting String with date and time results in correct DateTime format
        assertEquals(LocalDateTime.of(2024,2,1,10,11,12),
                Storage.convertStringToDateTime("2024-02-01T10:11:12"));
    }

    @Test
    public void convertStringToDateTime_invalidFormat_exceptionThrown() {
        try {
            assertEquals(LocalDateTime.of(2024,2,1,0,0,0),
                    Storage.convertStringToDateTime("2024-02-0"));
            fail();
        } catch (Exception e) {
            assertEquals("Text '2024-02-0' could not be parsed at index 8", e.getMessage());
        }
    }
}