package duke;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    private TaskList setupThreeTasks() throws DukeException {

            TaskList taskList = new TaskList();
            taskList.todocase("todo A");
            taskList.todocase("todo B");
            taskList.todocase("todo C");
            return taskList;

    }

    @Test
    public void addTask_normalInput_taskAdded() throws DukeException{
        TaskList taskList = this.setupThreeTasks();
        assertEquals(3, taskList.getSize());
        assertEquals(" A", taskList.getTask(0).description);
        assertEquals(" B", taskList.getTask(1).description);
        assertEquals(" C", taskList.getTask(2).description);
    }
}
