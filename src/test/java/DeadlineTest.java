import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import task.Deadline;
import task.Task;

public class DeadlineTest {

    @Test
    public void deadlineConstructTest() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dueDate = LocalDateTime.parse("2025-01-01 0000", dateFormat);
        Task task = new Deadline("Homework Test", dueDate);
        assertEquals(task.toString(), "[D][ ] Homework Test(by: " + dueDate.format(dateFormat) + ")");
    }

    @Test
    public void deadlineFormatPrintTest() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dueDate = LocalDateTime.parse("2025-01-01 0000", dateFormat);
        Task task = new Deadline("Homework Test 2", dueDate);
        task.mark();
        assertEquals(task.formatDataLine(), "Deadlines|"
                + task.getCompleted() + "|Homework Test 2|" + dueDate.format(dateFormat));
    }
}
