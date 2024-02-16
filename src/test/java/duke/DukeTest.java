package duke;

import duke.task.Task;
import duke.tasklist.TaskList;
import duke.task.ToDo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void addTask_increaseListSize() {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("read book");
        taskList.addTask(todo);

        assertEquals(1, taskList.getSize());
    }
}
