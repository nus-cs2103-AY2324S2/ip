
import duke.Tasks.TaskList;
import duke.Tasks.ToDo;

import org.junit.jupiter.api.Test;

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
