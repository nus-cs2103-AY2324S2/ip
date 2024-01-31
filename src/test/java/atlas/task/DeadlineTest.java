package atlas.task;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
    @Test
    public void createDeadline_ShouldHaveCorrectToString() {
        LocalDateTime dueDate = LocalDateTime.of(2021, 12, 31, 23, 59);
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        Deadline dl = new Deadline("Read book", dueDate);
        assertEquals("[D][ ] Read book (by: " + dueDate.format(FORMATTER) + ")", dl.toString());
    }
    @Test
    public void toggle_ShouldHaveCorrectToString() {
        LocalDateTime dueDate = LocalDateTime.of(2021, 12, 31, 23, 59);
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        Deadline dl = new Deadline("Read book", dueDate);
        dl.toggle();
        assertEquals("[D][X] Read book (by: " + dueDate.format(FORMATTER) + ")", dl.toString());

    }
}
