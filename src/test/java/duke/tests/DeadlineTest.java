/**
 * JUnit test class for the {@link duke.tasks.Deadlines} class.
 */
package duke.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadlines;

public class DeadlineTest {
    /**
     * JUnit test method for the {@link duke.tasks.Deadlines#toString()} method.
     * Tests the string representation of a Deadlines task.
     */
    @Test
    public void deadlineToStringTest() {
        // LocalDateTime object representing the deadline date and time
        LocalDateTime dateTime = LocalDateTime.of(2020, 1, 1, 19, 0);
        // Assertion to check if the string representation matches the expected output
        assertEquals("[D][ ] Find Bill(by: 01/01/2020 1900hrs)", new Deadlines("Find Bill", dateTime).toString());
    }
}

