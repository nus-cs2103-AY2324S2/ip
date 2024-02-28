package nicole.taskstorage;

import nicole.task.Event;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import nicole.nicoleexceptions.NicoleException;

public class TaskListTest {
    private final TaskList testList = new TaskList();

    @Test
    public void taskList_invalidTaskNumber_nicoleExceptionThrown() {
        try {
            testList.unmarkTask(TaskList.TASKS.size() + 1);
            fail();
        } catch (NicoleException e) {
            assertEquals(e.toString(),
                    "ERROR. Huh? That's not a valid item number :')");
        }
    }

    @Test
    public void taskList_clashingTasksGiven_nicoleExceptionThrown() {
        try {
            testList.addTask(new Event("from 2024-02-18 at 18:00:00 to 2024-02-18 at 19:00:00"));
            testList.addTask(new Event("from 2024-02-18 at 18:30:00 to 2024-02-18 at 19:30:00"));
            fail();
        } catch (NicoleException e) {
            assertEquals(e.toString(),
                         "ERROR. Careful! You already have an event at the same time");
        }
    }
}
