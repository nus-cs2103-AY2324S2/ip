package seedu.tasks;

import task.Deadline;
import task.InvalidDateException;
import task.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void test_deadline_DateTimeParseException_thrown() {
        try {
            new Deadline("go home", "29-03-2001");
            fail("Expected InvalidDateException to be thrown");
        } catch (InvalidInputException | InvalidDateException e) {
        }
    }

    @Test
    public void testToString() throws InvalidInputException, InvalidDateException {
        Deadline d = new Deadline("test code", "2001-03-29");
        assertEquals("[D][ ] test code (by: MARCH 29 2001)", d.toString());

        Deadline d1 = new Deadline("redo code", "2001-03-29");
        assertEquals("[D][ ] redo code (by: MARCH 29 2001)", d1.toString());
    }
}
