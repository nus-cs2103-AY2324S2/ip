package duke.utility;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void markDone_emptyTaskList_exceptionThrown() {
        TaskList taskList = new TaskList();
        try {
            taskList.markDone(1);
        } catch (DukeException e) {
            assertEquals("Task not found.", e.getMessage());
        }
    }

    @Test
    public void deleteTask_emptyTaskList_exceptionThrown() {
        TaskList taskList = new TaskList();
        try {
            taskList.deleteTask(1);
        } catch (DukeException e) {
            assertEquals("Task not found.", e.getMessage());
        }
    }

    @Test
    public void undo_emptyTaskList_exceptionThrown() {
        TaskList taskList = new TaskList();
        try {
            taskList.undo(1);
        } catch (DukeException e) {
            assertEquals("Task not found.", e.getMessage());
        }
    }
}
