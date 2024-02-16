package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the method in Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests the fileString method in the Deadline class.
     */
    @Test
    public void fileStringTest() {
        Deadline deadline = new Deadline("homework", LocalDate.parse("2024-04-04"));
        String expected = "D/ /homework/2024-04-04";
        String result = deadline.fileString();
        assertEquals(expected, result);
    }
}
