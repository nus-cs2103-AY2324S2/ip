package liv.container;

import liv.task.Deadline;
import liv.task.TodoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import liv.task.Task;
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
