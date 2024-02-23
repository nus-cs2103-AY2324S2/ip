package zhen.task;
import zhen.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class DeadlineTest {
    @Test
    public void testWellFormedDeadline() {
        String date = "2023-02-03";
        LocalDate date1 = Parser.parseDate(date);
        String input = new Deadline("return book", date1).toString();
        String expected = "[D][ ] return book (by: Feb 03 2023)";
        assertEquals(expected, input);
    }

    @Test
    public void testDateFirstDeadline() {
        String date = "2023-02-03";
        LocalDate localDate = Parser.parseDate(date);
        String input = new Deadline(localDate, "return book").toString();
        String expected = "[D][ ] return book (by: Feb 03 2023)";
        assertEquals(expected, input);
    }

    @Test
    public void testDeadlineStringInput() {
        String date = "2023-02-03";
        String input = new Deadline("return book", date).toString();
        String expected = "[D][ ] return book (by: 2023-02-03)";
        assertEquals(expected, input);
    }

    @Test
    public void testNullDateInput() {
        String date = null;
        String input = new Deadline("return book", date).toString();
        String expected = "[D][ ] return book (by: null)";
        assertEquals(expected, input);
    }

    @Test
    public void testNullMessageDeadline() {
        String date = null;
        String message = null;
        String input = new Deadline(message, date).toString();
        String expected = "[D][ ] null (by: null)";
        assertEquals(expected, input);
    }

    @Test
    public void nullDateDeadline() {
        LocalDate localDate = null;
        String input = new Deadline(localDate, "return book").toString();
        String expected = "[D][ ] return book (by: )";
        assertEquals(expected, input);
    }
}
