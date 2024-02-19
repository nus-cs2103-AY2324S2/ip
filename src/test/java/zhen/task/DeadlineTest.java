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
    public void testDeadlineStringInput() {
        String date = "2023-02-03";
        String input = new Deadline("return book", date).toString();
        String expected = "[D][ ] return book (by: 2023-02-03)";
        assertEquals(expected, input);
    }
}
