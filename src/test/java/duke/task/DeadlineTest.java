package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toString_validTimeFormat_success() throws DukeDateTimeParseException {
        // normal time, not done
        assertEquals("[D][ ] return book (due: Thu, Feb 1 2024 12:30)",
                new Deadline("return book", "2024-02-01 12:30").toString());

        // normal time, done
        assertEquals("[D][X] return book (due: Thu, Feb 1 2024 12:30)",
                new Deadline("return book", "2024-02-01 12:30", true).toString());
    }

    @Test
    public void serialize_validTimeFormat_success() throws DukeDateTimeParseException {
        // normal time, not done
        assertEquals("D | 0 | return book | 2024-02-01 12:30",
                new Deadline("return book", "2024-02-01 12:30").serialize());

        // normal time, done
        assertEquals("D | 1 | return book | 2024-02-01 12:30",
                new Deadline("return book", "2024-02-01 12:30", true).serialize());
    }
}
