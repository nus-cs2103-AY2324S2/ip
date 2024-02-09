package helpers;

import mocks.MockTodo;
import org.junit.jupiter.api.Test;
import tasks.Task;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    private final TaskList taskList;
    private final Task todo = new MockTodo(TODO_DESCRIPTION);
    private static final String TODO_DESCRIPTION = "borrow book";
    private static final int TASKLIST_LENGTH_AFTER_DELETE_TASK = 0;
    private static final int TASKLIST_LENGTH_AFTER_ADD_TASK = 1;

    public TaskListTest() {
        this.taskList = new TaskList();
    }

    @Test
    public void addTask_taskListLengthEqualsOne() {
        taskList.addTask(todo);
        assertEquals(TASKLIST_LENGTH_AFTER_ADD_TASK, taskList.getLength());
    }

    @Test
    public void markTask_taskMarked() {
        taskList.addTask(todo);
        taskList.markTask(TASKLIST_LENGTH_AFTER_ADD_TASK - 1);
        assertTrue(todo.getIsDone());
    }

    @Test
    public void unMarkTask_taskUnmarked() {
        taskList.addTask(todo);
        taskList.unmarkTask(TASKLIST_LENGTH_AFTER_ADD_TASK - 1);
        assertFalse(todo.getIsDone());
    }

    @Test
    public void deleteTask_taskListLengthEqualsZero() {
        taskList.addTask(todo);
        taskList.deleteTask(TASKLIST_LENGTH_AFTER_ADD_TASK - 1);
        assertEquals(TASKLIST_LENGTH_AFTER_DELETE_TASK, taskList.getLength());
    }
}
