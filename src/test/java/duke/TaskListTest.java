package duke;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    private TaskList setupThreeTasks() throws DukeException {

        TaskList taskList = new TaskList();
        taskList.todoCase("todo A");
        taskList.todoCase("todo B");
        taskList.todoCase("todo C");
        taskList.deadlineCase("deadline A /by 2019-10-15");
        taskList.eventCase("A /to 2019-10-15 /from 2019-10-15");
        return taskList;

    }

    @Test
    public void addTask_normalInput_taskAdded() throws DukeException {
        TaskList taskList = this.setupThreeTasks();
        assertEquals(5, taskList.getSize());
        assertEquals(" A", taskList.getTask(0).description);
        assertEquals(" B", taskList.getTask(1).description);
        assertEquals(" C", taskList.getTask(2).description);
        assertEquals(" A ", taskList.getTask(3).description);
        assertEquals("A", taskList.getTask(4).description);
    }
}
