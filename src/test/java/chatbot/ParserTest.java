package chatbot;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void testParseTodo() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> {
            String description = parser.parseDescription(" read book");
            Task task = new ToDo(description);
            assertEquals("read book", task.getDescription());
        });
    }
    @Test
    public void testParseDeadline() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> {
            String description = parser.parseDescription(" return book ");
            LocalDateTime by = parser.parseDateTime("01/12/2022 1800");
            Deadline task = new Deadline(description, by);
            assertEquals("return book", task.getDescription());
            assertEquals(LocalDateTime.of(2022, 12, 1, 18, 0), (task).getBy());
        });
    }

    @Test
    public void testParseEvent() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> {
            String description = parser.parseDescription(" book club ");
            LocalDateTime startTime = parser.parseDateTime("01/12/2022 1800");
            LocalDateTime endTime = parser.parseDateTime("01/12/2022 2000");
            Event task = new Event(description, startTime, endTime);
            assertEquals("book club", task.getDescription());
            assertEquals(LocalDateTime.of(2022, 12, 1, 18, 0), task.getStartTime());
            assertEquals(LocalDateTime.of(2022, 12, 1, 20, 0), task.getEndTime());
        });
    }
    @Test
    public void testParseIndex() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> {
            int index = parser.parseIndex("1");
            assertEquals(0, index);
        });
    }
    @Test
    public void testParseIndexException() {
        Parser parser = new Parser();
        try {
            parser.parseIndex("a");
        } catch (AlfredException e) {
            assertEquals("Sorry Master Bruce. Please enter a valid number.", e.getMessage());
        }
    }
    @Test
    public void testParseDateTime() {
        Parser parser = new Parser();
        assertDoesNotThrow(() -> {
            LocalDateTime dateTime = parser.parseDateTime("01/12/2022 1800");
            assertEquals(LocalDateTime.of(2022, 12, 1, 18, 0), dateTime);
        });
    }
    @Test
    public void testParseDateTimeException() {
        Parser parser = new Parser();
        try {
            parser.parseDateTime("01/12/2022");
        } catch (AlfredException e) {
            assertEquals("Sorry Master Bruce. Please enter a date and time.", e.getMessage());
        }
    }
}
