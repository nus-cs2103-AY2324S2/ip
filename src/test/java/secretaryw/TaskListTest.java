package secretaryw;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddTask() {
        // Create a TaskList object
        TaskList taskList = new TaskList();

        // Create a sample task
        Task task = new Task(TaskType.TODO, "Sample Task");

        // Add the task to the task list
        taskList.addTask(task);

        // Assert that the task list size is 1
        assertEquals(1, taskList.size());

        // Assert that the task added is the same as the one retrieved
        assertEquals(task, taskList.getTask(0));
    }

    @Test
    public void testDeleteTask() {
        // Create a TaskList object
        TaskList taskList = new TaskList();

        // Create sample tasks
        Task task1 = new Task(TaskType.TODO, "Task 1");
        Task task2 = new Task(TaskType.TODO, "Task 2");

        // Add tasks to the task list
        taskList.addTask(task1);
        taskList.addTask(task2);

        // Delete the first task
        taskList.deleteTask(0);

        // Assert that the task list size is 1
        assertEquals(1, taskList.size());

        // Assert that the remaining task is the second task
        assertEquals(task2, taskList.getTask(0));
    }
}
