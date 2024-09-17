package cruisey.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cruisey.exception.CruiseyException;
import cruisey.ui.Ui;

/**
 * JUnit tests for the {@link TaskList} class.
 */
public class TaskListTest {
    private Ui ui = new Ui();
    private TaskPriority priority;
    private TaskList taskList;

    /**
     * Set up the task list before each test.
     */
    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList< >(), ui);
    }

    /**
     * Tests the {@code addTask} method with a valid task.
     * Verifies that the task is added to the task list.
     */
    @Test
    void addTask_validTask_taskAdded() {
        Task task = new Task(TaskType.T, "Test Task", priority);
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.getTask(0));
    }

    /**
     * Tests the {@code addToDoTask} method with a valid ToDo task.
     * Verifies that the ToDo task is added to the task list.
     *
     * @throws CruiseyException If an error occurs while creating the ToDo task.
     */
    @Test
    void addToDoTask_validToDoTask_toDoTaskAdded() {
        ToDo toDoTask = null;
        try {
            toDoTask = new ToDo("Test ToDo Task", ui, priority);
        } catch (CruiseyException e) {
            throw new RuntimeException(e);
        }

        taskList.addToDoTask(toDoTask);
        assertEquals(1, taskList.size());
        assertEquals(toDoTask, taskList.getTask(0));
    }

    /**
     * Tests the {@code deleteTask} method with a valid index.
     * Verifies that the task at the specified index is deleted from the task list.
     */
    @Test
    void deleteTask_validIndex_taskDeleted() {

        Task task = new Task(TaskType.T, "Test Task", priority);

        taskList.addTask(task);

        assertDoesNotThrow(() -> taskList.deleteTask(0));
        assertEquals(0, taskList.size());
    }
}
