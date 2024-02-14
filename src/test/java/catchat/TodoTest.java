package catchat;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * TodoTest class contains the test cases for Todo class
 */
public class TodoTest {
    /**
     * Tests if markDone marks task as done
     */
    @Test
    public void test_markDone_taskUndone() {
        ArrayList<Task> taskList = new ArrayList<>();
        TaskList taskListObj = new TaskList(new Storage(taskList), taskList);
        taskListObj.addTask("todo task1");

        taskListObj.markDone(0);

        assertTrue(taskList.get(0).isDone());
    }
}
