package tofu;

import tofu.task.Deadline;
import tofu.task.Event;
import tofu.task.TaskList;
import tofu.task.ToDo;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    private static TaskList tasks;
    static {
        try {
            tasks = new TaskList(List.of(new ToDo("test"),
                    new Deadline("test", "2024-01-01"),
                    new Event("test", "2024-01-01", "2024-02-02")));
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    public void toStore_taskList_success() {

        assertEquals("T | false | test\n" +
                "D | false | test | 2024-01-01\n" +
                "E | false | test | 2024-01-01 | 2024-02-02", tasks.toStore());
    }

    public void toString_taskList_success() {
        assertEquals("1.[T][ ] test\n" +
                "2.[D][ ] test (by: Jan 1 2024)\n" +
                "3.[E][ ] test (from: Jan 1 2024 to: Feb 2 2024)", tasks.toString());
    }
}
