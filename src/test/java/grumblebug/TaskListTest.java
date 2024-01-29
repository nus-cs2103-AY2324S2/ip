package grumblebug;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    @Test
    public void addTest() {
        String testStr = "Hello";
        TaskList taskList = new TaskList();
        taskList.add(new Task(false, testStr));
        String expected = taskList.get(1).description;
        assertEquals(testStr, expected);
    }

    @Test
    public void sizeIncreasesTest() {
        String testStr = "Hello";
        TaskList taskList = new TaskList();
        int ini = taskList.size();
        taskList.add(new Task(false, testStr));
        int then = taskList.size();
        assertEquals(ini + 1, then);
    }
}
