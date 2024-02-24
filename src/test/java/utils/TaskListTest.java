package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mocks.MockTodo;
import tasks.Task;

/**
 * The TaskListTest class contains unit tests for the TaskList class.
 */
public class TaskListTest {
    private static final int TASKLIST_LENGTH_AFTER_ADD_TASK = 1;
    private static final int TASKLIST_LENGTH_AFTER_DELETE_TASK = 0;
    private static final String TODO_DESCRIPTION = "borrow book";
    private final TaskList taskList;
    private final Task todo = new MockTodo(TODO_DESCRIPTION);

    /**
     * Constructs a TaskListTest object and initializes the task list.
     */
    public TaskListTest() {
        this.taskList = new TaskList();
    }

    /**
     * Tests the addTask method of the TaskList class.
     * Ensures that task list length is incremented by one after adding a task.
     */
    @Test
    public void addTask_taskListLengthEqualsOne() {
        taskList.addTask(todo);
        assertEquals(TASKLIST_LENGTH_AFTER_ADD_TASK, taskList.getLength());
    }

    /**
     * Tests the markTask method of the TaskList class.
     * Ensures that a task is marked as done after calling markTask.
     */
    @Test
    public void markTask_taskMarked() {
        taskList.addTask(todo);
        taskList.markTask(TASKLIST_LENGTH_AFTER_ADD_TASK - 1);
        assertTrue(todo.isDone());
    }

    /**
     * Tests the unmarkTask method of the TaskList class.
     * Ensures that a task is marked as not done after calling unmarkTask.
     */
    @Test
    public void unMarkTask_taskUnmarked() {
        taskList.addTask(todo);
        taskList.unmarkTask(TASKLIST_LENGTH_AFTER_ADD_TASK - 1);
        assertFalse(todo.isDone());
    }

    /**
     * Tests the deleteTask method of the TaskList class.
     * Ensures that task list length is decremented by one after deleting a task.
     */
    @Test
    public void deleteTask_taskListLengthEqualsZero() {
        taskList.addTask(todo);
        taskList.deleteTask(TASKLIST_LENGTH_AFTER_ADD_TASK - 1);
        assertEquals(TASKLIST_LENGTH_AFTER_DELETE_TASK, taskList.getLength());
    }
}
