package duchess.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duchess.DuchessException;


/**
 * DeadlineTest class contains JUnit tests for the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests the toString method of the Deadline class.
     * It checks if the string representation of a deadline task is correct.
     */
    @Test
    public void deadlineToStringTest() {
        try {
            Deadline deadline = new Deadline("buy book", "18-01-2003 1800");
            assertEquals(deadline.toString(), "[D][ ] buy book (by: Jan 18 2003 06:00 PM)");
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }
    }
}
