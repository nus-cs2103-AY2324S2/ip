package univus.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void removeTest() {
        TaskList taskList = new TaskList();
        ArrayList<Task> store = new ArrayList<>();
        ToDo todo1 = new ToDo("todo return book");
        ToDo todo2 = new ToDo("todo read book");
        store.add(todo1);
        store.add(todo2);
        taskList.setTaskList(store);
        assertEquals(todo1, taskList.remove(1));
        assertEquals(1, taskList.size());
    }
}
