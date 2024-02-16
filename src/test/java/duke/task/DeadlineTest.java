package duke.task;

import duke.exception.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    void deadlineConstructor_throwsExceptionOnInvalidDate() {
        String input = "submit report /by invalid-date";
        Exception exception = assertThrows(DukeException.class, () -> new Deadline(input));

        String expectedMessage = " OOPS! Please enter deadline in a valid format (yyyy-mm-dd HH:mm). :(";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
