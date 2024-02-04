package toothless.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addEventToList_normalInput_success() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addEventToList("event", "2024-02-02 18:30", "2024-02-02 22:00");
        assertEquals("[E][ ] event (from: Feb 2 2024, 18:30 to: Feb 2 2024, 22:00)", 
                taskList.getTasks().get(0).toString());
    }

    @Test
    public void addEventToList_wrongDateTime_exceptionThrown() {
        TaskList taskList = new TaskList();
        try {
            taskList.addEventToList("event", "2024-13-02 25:30", "10pm");
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, /from and /to field datetime should use the following format: " +
                    "[yyyy-mm-dd hh:mm].", e.getMessage());
        }
    }
}
