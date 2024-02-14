package whisper;

import org.junit.jupiter.api.Test;
import whisper.WhisperException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    @Test
    void addTask_validTask_taskAdded() {
        // setup
        TaskList taskList = new TaskList();
        Task taskToAdd = new Task("Test Task: ", Task.TaskCategory.T);

        // execute
        taskList.addTask(taskToAdd);

        // verify
        ArrayList<Task> tasks = taskList.getTaskList();
        assertEquals(1, tasks.size());
        assertEquals(taskToAdd, tasks.get(0));
    }

    @Test
    void deleteTask_validIndex_taskDeleted() throws WhisperException {
        // setup
        TaskList taskList = new TaskList();
        Task taskToDelete = new Task("Test Task", Task.TaskCategory.T);
        taskList.addTask(taskToDelete);

        // execute
        taskList.deleteTask(0);

        // verify
        ArrayList<Task> tasks = taskList.getTaskList();
        assertEquals(0, tasks.size());
    }

    @Test
    void deleteTask_invalidIndex_exceptionThrown() {
        // setup
        TaskList taskList = new TaskList();

        // verify
        assertThrows(WhisperException.class, () -> taskList.deleteTask(0));
    }

    @Test
    void getTask_validIndex_taskReturned() throws WhisperException {
        // setup
        TaskList taskList = new TaskList();
        Task expectedTask = new Task("Test Task", Task.TaskCategory.T);
        taskList.addTask(expectedTask);

        // execute
        Task retrievedTask = taskList.getTask(0);

        // verify
        assertEquals(expectedTask, retrievedTask);
    }

    @Test
    void getTask_invalidIndex_exceptionThrown() {
        // Setup
        TaskList taskList = new TaskList();

        // Verify
        assertThrows(WhisperException.class, () -> taskList.getTask(0));
    }
}
