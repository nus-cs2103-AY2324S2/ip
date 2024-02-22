package jade.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The <code>DeadlineTest</code> class contains unit tests for the <code>Deadline</code> class,
 * which represents a deadline task within the task management system.
 * The above comment is generated using ChatGPT 3.5 using the prompt:
 * "generate a block comment for the DeadlineTest class:{code}".
 * Modified by author for higher quality.
 */
public class DeadlineTest {
    private LocalDateTime deadlineDateTime;
    private Deadline deadline1;
    private Deadline deadline2;
    @BeforeEach
    public void setUp() {
        deadlineDateTime = LocalDateTime.parse("2024-01-01 01:00 pm",
                DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm a", Locale.UK));
        deadline1 = new Deadline("read a book", deadlineDateTime);
        deadline2 = new Deadline("read a book", deadlineDateTime, true);
    }
    @Test
    public void isSameDate() {
        assertTrue(deadline1.isSameDate(LocalDate.parse("2024-01-01")));
        assertFalse(deadline1.isSameDate(LocalDate.parse("2024-02-01")));
    }

    @Test
    public void dateTimeFormatter() {
        assertEquals("Jan 1 2024 01:00 pm", deadline1.dateTimeFormatter());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] read a book (by: Jan 1 2024 01:00 pm)", deadline1.toString());
        assertEquals("[D][X] read a book (by: Jan 1 2024 01:00 pm)", deadline2.toString());
    }
}
