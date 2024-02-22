package atlas.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {

    private final static int DEFAULT_PRIORITY = 3;

    @Test
    public void createDeadlineShouldHaveCorrectToString() {
        LocalDateTime dueDate = LocalDateTime.of(2021, 12, 31, 23, 59);
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        Deadline dl = new Deadline("Read book", dueDate, DEFAULT_PRIORITY);
        assertEquals(dl.toString(), "[D][ ] Read book " + "(P:" + DEFAULT_PRIORITY + ") "
                + "(by: " + dueDate.format(FORMATTER) + ")");
    }

    @Test
    public void toggleShouldHaveCorrectToString() {
        LocalDateTime dueDate = LocalDateTime.of(2021, 12, 31, 23, 59);
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        Deadline dl = new Deadline("Read book", dueDate, DEFAULT_PRIORITY);
        dl.toggle();
        assertEquals(dl.toString(), "[D][X] Read book " + "(P:" + DEFAULT_PRIORITY + ") "
                + "(by: " + dueDate.format(FORMATTER) + ")");
    }
}
