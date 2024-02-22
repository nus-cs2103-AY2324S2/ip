package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Tests for TaskList class.
 *
 * @see TaskList
 */
class TaskListTest {

    /**
     * Tests for addTask method.
     */
    @Test
    void addTask_success() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = new Todo("test");
        taskList.addTask(task);
        assertEquals(taskList.getSize(), 1);
    }

    /**
     * Tests for addTask failure for null added.
     */
    @Test
    void addTask_fail() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        assertFalse(taskList.addTask(null));
    }

    /**
     * Tests for deleteTask method.
     */
    @Test
    void deleteTask_success() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = new Todo("test");
        taskList.addTask(task);
        assertEquals(taskList.deleteTask(0), task);
    }

    /**
     * Tests for deleteTask method.
     */
    @Test
    void deleteTask_negativeIndex() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        Task task = new Todo("test");
        taskList.addTask(task);
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(-100));
    }

    /**
     * Tests for deleteTask failure for invalid index.
     */
    @Test
    void deleteTask_fail() {
        TaskList taskList = new TaskList(new ArrayList<Task>());
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(100));
    }
}
