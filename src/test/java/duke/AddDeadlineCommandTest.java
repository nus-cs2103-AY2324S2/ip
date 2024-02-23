package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class AddDeadlineCommandTest {
    @Test
    public void addDeadlineCommandTest() {
        try {
            assertEquals(
                    new AddDeadlineCommand("return book /by 2024-02-23 23:59").toString(),
                    "AddTaskCommand: [D][ ] return book (by: Feb 23 2024 23:59)");
        } catch (InvalidCommandException e) {
            fail("Error should not be raised");
        }
    }
}
