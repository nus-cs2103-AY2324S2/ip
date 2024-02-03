package objects;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateTasksTest {

    @Test
    void testDeadlinesToString() {
        LocalDateTime deadlineDateTime = LocalDateTime.of(2024, 2, 3, 12, 0);
        Deadlines deadlineTask = new Deadlines("Complete assignment", deadlineDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String expectedOutput = String.format("[D][ ] %s (by: %s)", "Complete assignment", deadlineDateTime.format(formatter));

        assertEquals(expectedOutput, deadlineTask.toString());
    }

    @Test
    void testEventsToString() {
        LocalDateTime fromDateTime = LocalDateTime.of(2024, 2, 3, 10, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2024, 2, 3, 14, 0);
        Events eventTask = new Events("Team meeting", fromDateTime, toDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String expectedOutput = String.format("[E][ ] %s (from: %s to: %s)", "Team meeting", fromDateTime.format(formatter), toDateTime.format(formatter));

        assertEquals(expectedOutput, eventTask.toString());
    }

    @Test
    void testToDosToString() {
        ToDos toDoTask = new ToDos("Buy groceries");

        String expectedOutput = String.format("[T][ ] %s", "Buy groceries");

        assertEquals(expectedOutput, toDoTask.toString());
    }
}
