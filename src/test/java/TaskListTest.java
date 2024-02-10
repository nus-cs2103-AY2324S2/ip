
import duke.tasks.TaskList;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    /*
    public TaskList taskList;

    public TaskListTest() {
        this.taskList = new TaskList();
    }
    */

    public void dummyTest() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getTasksList(), new ArrayList<>());
    }

}
