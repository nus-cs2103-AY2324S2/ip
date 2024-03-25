package riz.data;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTaskListTest() {
        ArrayList<Task> aL = new ArrayList<>();
        TaskList taskList = new TaskList(aL);
        ArrayList<Task> result = taskList.getTaskList();
        assertEquals(aL, result);
    }

    @Test
    public void sizeTest() {
        ArrayList<Task> aL = new ArrayList<>();
        TaskList taskList = new TaskList(aL);
        taskList.add(new ToDo("shower"));
        taskList.add(new ToDo("shave"));
        taskList.add(new ToDo("brush"));
        taskList.remove(1);
        assertEquals(2, taskList.size());
    }
}
