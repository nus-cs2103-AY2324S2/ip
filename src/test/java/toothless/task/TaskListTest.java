package toothless.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addEventToList_normalInput_success() throws Exception {
        TaskList tasks = new TaskList();
        tasks.addEventToList("event", "2024-02-02 18:30", "2024-02-02 22:00");
        assertEquals("[E][ ] event (from: Feb 2 2024, 18:30 to: Feb 2 2024, 22:00)\n\tTags: NIL",
                tasks.getTasks().get(0).toString());
    }

    @Test
    public void addEventToList_wrongDateTime_exceptionThrown() {
        TaskList tasks = new TaskList();
        try {
            tasks.addEventToList("event", "2024-13-02 25:30", "10pm");
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, /from and /to field datetime should be in the following format: "
                    + "yyyy-mm-dd hh:mm", e.getMessage());
        }
    }
}
