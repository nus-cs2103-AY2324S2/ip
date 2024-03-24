package duke.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void getTaskIcon_success() {
        Event event = new Event("Project Meeting", false, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        assertEquals("[E]", event.getTaskIcon());
    }

    @Test
    public void getStatusIcon_notCompleted() {
        Event event = new Event("Project Meeting", false, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        assertEquals("[ ]", event.getStatusIcon());
    }

    @Test
    public void getStatusIcon_completed() {
        Event event = new Event("Project Meeting", true, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        assertEquals("[X]", event.getStatusIcon());
    }

    @Test
    public void getTaskDescription_success() {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusHours(2);
        Event event = new Event("Project Meeting", false, from, to);
        String expectedDescription = "Project Meeting (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        assertEquals(expectedDescription, event.getTaskDescription());
    }

    @Test
    public void trimDescription_success() {
        Event event = new Event("event Project Meeting /from 2023/03/21 14:00 /to 2023/03/21 16:00", false, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        assertEquals("Project Meeting", event.trimDescription("event Project Meeting /from 2023/03/21 14:00 /to 2023/03/21 16:00"));
    }

    @Test
    public void getFrom_success() {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusHours(2);
        Event event = new Event("Project Meeting", false, from, to);
        assertEquals(from, event.getFrom());
    }

    @Test
    public void getTo_success() {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusHours(2);
        Event event = new Event("Project Meeting", false, from, to);
        assertEquals(to, event.getTo());
    }
}