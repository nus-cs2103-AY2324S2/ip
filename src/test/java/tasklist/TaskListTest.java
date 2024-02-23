package tasklist;
import tasks.Task;
import tasks.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void listSizeTest() {
        Task t = new Todo("test");
        TaskList list = new TaskList(new ArrayList<Task>());
        list.addToList(t);
        assertEquals(list.getListSize(), 1);
    }

    @Test
    public void deleteTest() {
        Task t = new Todo("test");
        TaskList list = new TaskList(new ArrayList<Task>());
        list.addToList(t);
        assertEquals(list.getListSize(), 1);
        list.deleteTask(1);
        assertEquals(list.getListSize(), 0);
    }
}
