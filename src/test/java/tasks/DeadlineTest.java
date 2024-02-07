package tasks;

import duke.tasks.Deadline;

import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void createDeadlineTest() {
        assertEquals("[D][ ] return book (by: Jan 25 2024 05:00 pm)",
                new Deadline("return book", "25-01-2024 17:00").toString());
    }

    @Test
    public void differentDateFormatTest() {
        assertEquals("[D][ ] return book (by: Jan 25 2024 05:00 pm)",
                new Deadline("return book", "2024/01/25 05:00 pm").toString());
    }

    @Test
    public void differentTimeFormatTest() {
        assertEquals("[D][ ] return book (by: Jan 25 2024 05:00 pm)",
                new Deadline("return book", "2024/01/25 1700").toString());
    }

    @Test
    public void testSaveFileSyntax() {
        assertEquals("D | 0 | return book | 2024-01-25 17:00",
                new Deadline("return book", "2024/01/25 1700").getSaveTask());
    }
}
