package objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class CreateTasksTest {

    @Test
    void testDeadlinesToString() {
        LocalDateTime deadlineDateTime = LocalDateTime.of(2024, 2, 3, 12, 0);
        Deadline deadlineTask = new Deadline("Complete assignment", deadlineDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String expectedOutput = String.format("[D][ ] %s (by: %s)", "Complete assignment",
                deadlineDateTime.format(formatter));

        assertEquals(expectedOutput, deadlineTask.toString());
    }

    @Test
    void testEventsToString() {
        LocalDateTime fromDateTime = LocalDateTime.of(2024, 2, 3, 10, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2024, 2, 3, 14, 0);
        Event eventTask = new Event("Team meeting", fromDateTime, toDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String expectedOutput = String.format("[E][ ] %s (from: %s to: %s)", "Team meeting",
                fromDateTime.format(formatter), toDateTime.format(formatter));

        assertEquals(expectedOutput, eventTask.toString());
    }

    @Test
    void testToDosToString() {
        ToDo toDoTask = new ToDo("Buy groceries");

        String expectedOutput = String.format("[T][ ] %s", "Buy groceries");

        assertEquals(expectedOutput, toDoTask.toString());
    }
}
