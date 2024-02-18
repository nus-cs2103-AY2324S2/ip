package asher.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TaskListTest {
    @Test
    public void testAddAndGetTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Task 1");
        taskList.addTask(task);

        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testAddAndDeleteTask() {
        TaskList taskList = new TaskList();
        Task task = new Task("Task 1");
        taskList.addTask(task);
        Task deletedTask = taskList.deleteTask(0);

        assertEquals(task, deletedTask);
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void testEmptyTaskList() {
        TaskList taskList = new TaskList();

        assertEquals(0, taskList.getSize());
        assertNull(taskList.get(0));
    }

    @Test
    public void testFullTaskList() {
        TaskList taskList = new TaskList();

        for (int i = 0; i < TaskList.MAX_TASKS; i++) {
            taskList.addTask(new Task("Task " + (i + 1)));
        }

        taskList.addTask(new Task("Extra Task"));
        assertEquals(TaskList.MAX_TASKS, taskList.getSize());
    }
}
