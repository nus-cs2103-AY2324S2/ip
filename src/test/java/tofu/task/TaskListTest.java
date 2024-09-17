package tofu.task;

import tofu.TofuException;
import tofu.task.TaskList;
import tofu.task.ToDo;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    private static TaskList tasks = new TaskList(List.of(
            new ToDo("sleep"),
            new ToDo("Math test"),
            new ToDo("Science test")));


    @Test
    public void toStore_taskList_success() {
        assertEquals("T | false | sleep\n" +
                "T | false | Math test\n" +
                "T | false | Science test\n", tasks.toStore());
    }

    @Test
    public void toString_taskList_success() {
        assertEquals("    1. [T][ ] sleep\n" +
                "    2. [T][ ] Math test\n" +
                "    3. [T][ ] Science test\n", tasks.toString());
    }

    @Test
    public void find_taskList_success() {
        assertEquals(new TaskList(List.of(
                new ToDo("Math test"),
                new ToDo("Science test"))), tasks.find("test"));
        assertEquals(new TaskList(List.of(new ToDo("sleep"))), tasks.find("sleep"));
    }
}
