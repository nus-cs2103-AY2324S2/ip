package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

public class DukeTest {
    @Test
    public void addTask_increaseListSize() {
        TaskList taskList = new TaskList();
        Task todo = new ToDo("read book");
        taskList.addTask(todo);

        assertEquals(1, taskList.getSize());
    }
}
