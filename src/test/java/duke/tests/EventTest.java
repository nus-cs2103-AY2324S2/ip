/**
 * JUnit test class for the {@link duke.tasks.Events} class.
 */
package duke.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.tasks.Events;

public class EventTest {
    /**
     * JUnit test method for the {@link duke.tasks.Events#toString()} method.
     * Tests the string representation of an Events task.
     */
    @Test
    public void eventToStringTest() {
        // LocalDateTime objects representing the start and end date and time of the event
        LocalDateTime dateTime1 = LocalDateTime.of(2020, 1, 1, 15, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2020, 1, 1, 16, 0);
        // Assertion to check if the string representation matches the expected output
        assertEquals("[E][ ] Meet Prof Damith(from: 01/01/2020 1500hrs, to: 01/01/2020 1600hrs)",
                    new Events("Meet Prof Damith", dateTime1, dateTime2).toString());
    }
}

