package liv.container;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import liv.task.Task;
import liv.task.TodoTask;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList();
        Task task = new TodoTask("Do CS2103T iP");
        tasks.addTask(task);
        assertEquals(1, TaskList.getListSize());
        assertEquals(task, TaskList.getTask(0));
    }
}
