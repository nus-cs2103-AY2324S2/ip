package jimmy.essentials;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jimmy.exceptions.JimmyException;

public class TaskListTest {
    private final TaskList taskList = new TaskList();

    @Test
    public void testCreateNewTask_invalidInstruction_exceptionThrown() {
        String invalidTaskType = "invalid";
        String details = "invalid task details";
        Assertions.assertThrows(JimmyException.class, () -> taskList.createNewTask(invalidTaskType, details));
    }

    @Test
    public void testCreateNewTask_invalidDeadline_exceptionThrown() {
        String invalidDeadline = "A /by 08-12-2021";
        Assertions.assertThrows(JimmyException.class, () -> taskList.createNewTask("deadline", invalidDeadline));
    }

    @Test
    public void testCreateNewTask_invalidEvent_exceptionThrown() {
        String invalidEvent = "A /from 08-12-2021 /to 09-12-2021";
        Assertions.assertThrows(JimmyException.class, () -> taskList.createNewTask("event", invalidEvent));
    }

    @Test
    public void testCreateNewTask_invalidDateTimeFormat_exceptionThrown() {
        String invalidDeadline = "A /by 12/08/2025";
        Assertions.assertThrows(JimmyException.class, () -> taskList.createNewTask("deadline", invalidDeadline));
    }
}
