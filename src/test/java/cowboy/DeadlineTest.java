package cowboy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    /**
     * Tests if getTaskType returns correct task type
     */
    @Test
    public void test_getTaskType_task() {
        ArrayList<Task> taskList = new ArrayList<>();
        TaskList taskListObj = new TaskList(new Storage(taskList), taskList);
        taskListObj.addTask("deadline deadline1 /by 2020-12-12");

        taskListObj.getTaskType("deadline deadline1 /by 2020-12-12");

        assertEquals(taskList.get(0).getTaskType(), "D");
    }
}
