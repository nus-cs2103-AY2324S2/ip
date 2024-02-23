package Duke;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskHandlerTest {

    @Test
    public void testCheckSchedule() {
        Deadline deadline1 = new Deadline("Test", LocalDate.of(2022, 1, 1));
        Event event = new Event("Test", LocalDate.of(2022,1, 1),
                LocalDate.of(2022, 3,1));
        Deadline deadline2 = new Deadline("Test", LocalDate.of(2020, 1, 1));
        ToDo todo = new ToDo("Test");
        TaskList output = new TaskList();
        TaskList input = new TaskList();
        output.add(deadline1);
        output.add(event);
        input.add(deadline1);
        input.add(event);
        input.add(todo);
        // Asserts output is only deadline and event tasks
        assertEquals(output, TaskHandler.checkSchedule("1/1/2022",
                "1/1/2023", input));
        input.add(deadline2);
        output.add(deadline2);
        // Asserts output is only task that is in the date range
        assertNotEquals(output, TaskHandler.checkSchedule("1/1/2022",
                "1/1/2023", input));

    }
}
