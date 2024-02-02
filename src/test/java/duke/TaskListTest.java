package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() throws DukeException {
        taskList = new TaskList();
        task1 = new Todo("read book");
        task2 = new Deadline("return book", "2021-10-10");
    }

    @Test
    public void addTask_validTask_tasksAddedCorrectly() {
        taskList.addTask(task1);
        assertEquals(1, taskList.getSize());
        taskList.addTask(task2);
        assertEquals(2, taskList.getSize());
    }

    @Test
    public void removeTask_validIndex_taskRemovedCorrectly() throws DukeException {
        taskList.addTask(task1);
        taskList.addTask(task2);
        Task removedTask = taskList.removeTask(0);
        assertEquals(task1, removedTask);
        assertEquals(1, taskList.getSize());
    }

    @Test
    public void getTask_validIndex_correctTaskReturned() throws DukeException {
        taskList.addTask(task1);
        taskList.addTask(task2);
        Task retrievedTask = taskList.getTask(1);
        assertEquals(task2, retrievedTask);
    }

    @Test
    public void removeTask_invalidIndex_throwsException() {
        taskList.addTask(task1);
        assertThrows(DukeException.class, () -> taskList.removeTask(2));
    }

    @Test
    public void getTask_invalidIndex_throwsException() {
        taskList.addTask(task1);
        assertThrows(DukeException.class, () -> taskList.getTask(2));
    }
}
