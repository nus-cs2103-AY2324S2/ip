package fluffy.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fluffy.FluffyException;
import fluffy.task.Task;
import fluffy.task.Todo;

public class TaskListTest {

    @Test
    public void addTask_validTask_success() {
        TaskList tasks = new TaskList();
        Task task = new Todo("task");
        tasks.addTask(task);
        assertEquals(1, tasks.getSize());
    }

    @Test
    public void addTask_validTasks_success() {
        TaskList tasks = new TaskList();
        Task task1 = new Todo("task1");
        Task task2 = new Todo("task2");
        tasks.addTask(task1);
        tasks.addTask(task2);
        assertEquals(2, tasks.getSize());
    }

    @Test
    public void deleteTask_validIndex_success() throws FluffyException {
        TaskList tasks = new TaskList();
        Task task = new Todo("task");
        tasks.addTask(task);
        tasks.deleteTask(0);
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        Task task = new Todo("task");
        tasks.addTask(task);
        assertThrows(FluffyException.class, () -> tasks.deleteTask(1));
    }

    @Test
    public void getTask_validIndex_success() throws FluffyException {
        TaskList tasks = new TaskList();
        Task task = new Todo("task");
        tasks.addTask(task);
        Task resultTask = tasks.getTask(0);
        assertEquals(task, resultTask);
    }

    @Test
    public void getTask_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        Task task = new Todo("task");
        tasks.addTask(task);
        assertThrows(FluffyException.class, () -> tasks.getTask(1));
    }
}
