package duke.task;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void getTaskIcon_success() {
        Deadline deadline = new Deadline("Submit report", false, LocalDateTime.now());
        assertEquals("[D]", deadline.getTaskIcon());
    }

    @Test
    public void getStatusIcon_notCompleted() {
        Deadline deadline = new Deadline("Submit report", false, LocalDateTime.now());
        assertEquals("[ ]", deadline.getStatusIcon());
    }

    @Test
    public void getStatusIcon_completed() {
        Deadline deadline = new Deadline("Submit report", true, LocalDateTime.now());
        assertEquals("[X]", deadline.getStatusIcon());
    }

    @Test
    public void getTaskDescription_success() {
        LocalDateTime by = LocalDateTime.now().plusDays(1);
        Deadline deadline = new Deadline("Submit report", false, by);
        String expectedDescription = "Submit report (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
        assertEquals(expectedDescription, deadline.getTaskDescription());
    }

    @Test
    public void trimDescription_success() {
        Deadline deadline = new Deadline("deadline Submit report /by 2023/12/31 23:59", false, LocalDateTime.now());
        assertEquals("Submit report", deadline.trimDescription("deadline Submit report /by 2023/12/31 23:59"));
    }

    @Test
    public void getBy_success() {
        LocalDateTime by = LocalDateTime.now().plusDays(1);
        Deadline deadline = new Deadline("Submit report", false, by);
        assertEquals(by, deadline.getBy());
    }

}

