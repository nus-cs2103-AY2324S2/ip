package ciara.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import ciara.exceptions.CiaraException;

/**
 * Test cases for methods in the Deadline Class
 *
 * @author RyanNgWH
 */
@TestInstance(Lifecycle.PER_CLASS)
public class DeadlineTest {
    /**
     * Tests isOn method successful
     */
    @Test
    public void isOn_correctDate_success() throws CiaraException {
        Deadline deadline = new Deadline("Nights To Days", Instant.ofEpochSecond(1706500800));

        assertEquals(true, deadline.isOn(Instant.ofEpochSecond(1706457600)));
        assertEquals(false, deadline.isOn(Instant.ofEpochSecond(1706587200)));
    }
}
