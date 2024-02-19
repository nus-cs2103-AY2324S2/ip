package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.run.TaskList;
import duke.tasks.Task;
import duke.tasks.TodoTask;


public class TaskListTest {
    @Test
    public void taskListConstructionTest() {
        ArrayList<Task> testArray = new ArrayList<Task>();
        Task testTask = new TodoTask("janna", false);
        testArray.add(testTask);
        TaskList list = new TaskList(testArray);
        assertEquals(testTask, list.getTask(0));
    }

    @Test
    public void taskListMethodsTest() {
        ArrayList<Task> testArray = new ArrayList<Task>();
        Task testTask = new TodoTask("chua", true);
        testArray.add(testTask);
        TaskList list = new TaskList(testArray);

        assertEquals(testArray, list.getList());

        list.removeTask(0);
        assertEquals(true, list.getList().isEmpty());

        assertEquals(0, list.getSize());
    }
}
