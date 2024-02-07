package victor.tasktype;

import victor.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void ToDoTest() {
        TaskList tasks = new TaskList();
        Task todoTask = new Todo("Todo Test 1", false);

        tasks.addTask(todoTask);

        assertEquals(tasks.getPosValue(0),todoTask);
        assertEquals("[T][ ] Todo Test 1",todoTask.toString());
    }
}
