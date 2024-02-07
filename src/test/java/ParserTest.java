import duke.Deadline;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void testAddTask() {
        // Given
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Event("CS2103T tutorial", "Mon 4pm", "6pm");

        // When
        taskList.addTask(task);

        // Then
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.get(0));
    }

    @Test
    public void testSetDone() {
        // Given
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new ToDo("Buy groceries");
        taskList.addTask(task);

        // When
        taskList.setDone(0);

        // Then
        assertTrue(task.isDone());
    }

    @Test
    public void testSetUndone() {
        // Given
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new ToDo("Buy groceries");
        task.setDone();
        taskList.addTask(task);

        // When
        taskList.setUndone(0);

        // Then
        assertFalse(task.isDone());
    }

    @Test
    public void testDeleteTask() {
        // Given
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task1 = new ToDo("Buy groceries");
        Task task2 = new Deadline("Finish report", LocalDateTime.now());
        taskList.addTask(task1);
        taskList.addTask(task2);

        // When
        taskList.deleteTask(0);

        // Then
        assertEquals(1, taskList.size());
        assertEquals(task2, taskList.get(0));
    }
}
