package yue.tasks;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTaskTest {

    @Test
    public void testFormatDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 30, 15, 30);

        DeadlineTask task = new DeadlineTask("Test Deadline", "2024-01-30 1530");


        String formattedDateTime = task.formatDateTime(dateTime);

        assertEquals("Jan 30 2024 15:30", formattedDateTime);
    }
}

