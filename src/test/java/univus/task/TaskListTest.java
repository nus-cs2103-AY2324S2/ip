package univus.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void removeTest() {
        TaskList taskList = new TaskList();
        ToDo todo1 = new ToDo("todo return book");
        ToDo todo2 = new ToDo("todo read book");
        taskList.add(todo1);
        taskList.add(todo2);
        assertEquals(todo1, taskList.remove(1));
        assertEquals(1, taskList.size());
    }
}
