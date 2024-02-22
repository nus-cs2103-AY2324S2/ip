package ciara.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import ciara.exceptions.CiaraException;

/**
 * Test cases for methods in the Event Class
 *
 * @author RyanNgWH
 */
@TestInstance(Lifecycle.PER_CLASS)
public class EventTest {
    /**
     * Test encompasses method successful
     */
    @Test
    public void encompasses_correctDate_success() throws CiaraException {
        Event event = new Event("Nights To Days", Instant.ofEpochSecond(1706500800), Instant.ofEpochSecond(1706846400));

        assertEquals(true, event.encompasses(Instant.ofEpochSecond(1706457600)));
        assertEquals(true, event.encompasses(Instant.ofEpochSecond(1706760000)));
        assertEquals(false, event.encompasses(Instant.ofEpochSecond(1707537600)));
    }
}
