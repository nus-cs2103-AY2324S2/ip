package tofu;

import tofu.TofuException;
import tofu.task.TaskList;
import tofu.task.ToDo;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    private static TaskList tasks = new TaskList(List.of(
            new ToDo("sleep"),
            new ToDo("Math test"),
            new ToDo("Science test")));


    public void toStore_taskList_success() {

        assertEquals("T | false | sleep\n" +
                "T | false | Math test\n" +
                "T | false | Science test\n", tasks.toStore());
    }

    public void toString_taskList_success() {
        assertEquals("    1. [T][ ] sleep\n" +
                "    2. [T][ ] Math test\n" +
                "    3. [T][ ] Science test\n", tasks.toString());
    }

    public void find_taskList_success() {
        assertEquals("    1. [T][ ] Math test\n" +
                "    2. [T][ ] Science test\n", tasks.find("test"));
        assertEquals("    1. [T][ ] sleep\n", tasks.find("sleep"));
    }
}
