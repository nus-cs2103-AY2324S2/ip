package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private Deadline deadline;

    @BeforeEach
    public void setUp() throws DukeException {
        deadline = new Deadline("return book", "2024-10-10");
    }

    @Test
    public void deadlineCreation_validDetails_correctStringRepresentation() {
        assertEquals("[D][ ] return book (by: Oct 10 2024)", deadline.toString());
    }

    @Test
    public void deadlineCreation_invalidDateFormat_throwsException() {
        assertThrows(DukeException.class, () -> new Deadline("return book", "2023-02-3"));
    }

    @Test
    public void markAsDone_deadlineNotDoneBefore_markedAsDone() {
        deadline.markAsDone();
        assertEquals("[D][X] return book (by: Oct 10 2024)", deadline.toString());
    }

    @Test
    public void markAsUndone_deadlineInitiallyDone_markedAsNotDone() {
        deadline.markAsDone();
        deadline.unmark();
        assertEquals("[D][ ] return book (by: Oct 10 2024)", deadline.toString());
    }
}
