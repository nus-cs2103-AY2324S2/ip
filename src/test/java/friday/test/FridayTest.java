package friday.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import friday.task.TaskList;
import friday.task.Todo;

public class FridayTest {
    private static final String DATA_FILE_PATH = "./src/main/java/data/Friday.txt";

    @Test
    public void testAddTodo() {
        TaskList taskList = new TaskList(DATA_FILE_PATH);
        taskList.addTodo("todo Testing Todo");
        Todo t = new Todo("Testing Todo");

        assertEquals(1, taskList.getLength());
        assertEquals(t.toString(), taskList.getTask(0).toString());
    }

    @Test
    public void testDeleteTask() {
        TaskList taskList = new TaskList(DATA_FILE_PATH);

        taskList.addTodo("todo Testing Delete");
        taskList.deleteTask("delete 1");

        assertEquals(0, taskList.getLength());
    }
}
