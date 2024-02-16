package demon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A test of the Deadline class, toString method.
 */
public class DeadlineTest {
    @Test
    public void stringMsg_test() {
        String expected = "[D][ ] return book (by: Mar 3 2024 10:00 PM)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse("03/03/2024 2200", formatter);
        Deadline deadlineTest = new Deadline("return book", dateTime);
        assertEquals(expected, deadlineTest.toString(),"String returns unexpected result");
    }
}
