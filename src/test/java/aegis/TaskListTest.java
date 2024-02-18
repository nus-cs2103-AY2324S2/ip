package aegis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void markTaskValidIndexTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Dummy task 1"));
        taskList.addTask(new Deadline("Dummy task 2", "2024-11-06"));
        taskList.addTask(new Event("Dummy task 3", "2024-11-06", "2024-11-07"));

        taskList.markTask(0);
        assertEquals(taskList.getTask(0).getStatusInt(), 1);

        taskList.markTask(1);
        assertEquals(taskList.getTask(1).getStatusInt(), 1);

        taskList.markTask(2);
        assertEquals(taskList.getTask(2).getStatusInt(), 1);
    }

    @Test
    public void markTaskInvalidIndexTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Dummy task 1"));
        taskList.addTask(new Deadline("Dummy task 2", "2024-11-06"));
        taskList.addTask(new Event("Dummy task 3", "2024-11-06", "2024-11-07"));

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(-1));

        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTask(1000));
    }
}

