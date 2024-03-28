package pyrite;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import pyrite.task.Deadline;
import pyrite.task.Event;
import pyrite.task.Task;
import pyrite.task.ToDo;

/**
 * Test cases for TaskList class.
 */
public class TaskListTest {
    // Test cases suggested by Github Copilot
    /**
     * Test case for size method with empty list.
     */
    @Test
    public void size_emptyList_success() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
    }
    /**
     * Test case for size method with multiple tasks.
     */
    @Test
    public void size_multipleTasks_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        tasks.add(new ToDo("test"));
        tasks.add(new ToDo("test"));
        assertEquals(3, tasks.size());
    }
    /**
     * Test case for toString method with empty list.
     */
    @Test
    public void toString_emptyList_success() {
        TaskList tasks = new TaskList();
        assertEquals("", tasks.toString());
    }
    /**
     * Test case for toString method with single task.
     */
    @Test
    public void toString_singleTask_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        assertEquals("1. [T][ ] test", tasks.toString());
    }
    /**
     * Test case for toString method with multiple tasks.
     */
    @Test
    public void toString_multipleTasks_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        tasks.add(
                new Deadline("test", LocalDateTime.parse("2024-01-01T00:00")));
        tasks.add(
                new Event("test",
                        LocalDateTime.parse("2024-01-01T00:00"),
                        LocalDateTime.parse("2024-01-01T00:00")));
        assertEquals(
                "1. [T][ ] test\n"
                        + "2. [D][ ] test (by: Jan 1 2024, 00:00)\n"
                        + "3. [E][ ] test (from: Jan 1 2024, 00:00 to: Jan 1 2024, 00:00)",
                tasks.toString());
    }
    /**
     * Test case for setStatus method with valid id.
     */
    @Test
    public void setStatus_validId_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        tasks.setStatus(0, Task.Status.Done);
        assertEquals("[T][X] test", tasks.toString(0));
    }
    /**
     * Test case for setStatus method with invalid id.
     */
    @Test
    public void setStatus_invalidId_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        try {
            tasks.setStatus(1, Task.Status.Done);
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }
    /**
     * Test case for remove method with valid id.
     */
    @Test
    public void remove_validId_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        tasks.remove(0);
        assertEquals("", tasks.toString());
    }
    /**
     * Test case for add method with valid id.
     */
    @Test
    public void add_validId_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        assertEquals("1. [T][ ] test", tasks.toString());
    }
    /**
     * Test case for isValidId method with valid id.
     */
    @Test
    public void isValidId_validId_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        assertEquals(true, tasks.isValidId(0));
    }
    /**
     * Test case for isValidId method with invalid id.
     */
    @Test
    public void isValidId_invalidId_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        assertEquals(false, tasks.isValidId(1));
    }
    /**
     * Test case for isValidId method with negative id.
     */
    @Test
    public void isValidId_negativeId_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        assertEquals(false, tasks.isValidId(-1));
    }
    /**
     * Test case for isValidId method with empty list.
     */
    @Test
    public void isValidId_emptyList_success() {
        TaskList tasks = new TaskList();
        assertEquals(false, tasks.isValidId(0));
    }

}
