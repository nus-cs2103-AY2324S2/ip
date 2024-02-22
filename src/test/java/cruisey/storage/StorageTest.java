package cruisey.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cruisey.exception.CruiseyException;
import cruisey.task.Deadline;
import cruisey.task.Event;
import cruisey.task.Task;
import cruisey.task.TaskList;
import cruisey.task.TaskPriority;
import cruisey.task.ToDo;
import cruisey.ui.Ui;

/**
 * JUnit tests for the {@link Storage} class.
 */
public class StorageTest {

    private Storage storage;
    private TaskList taskList;

    private Ui ui = new Ui();
    private TaskPriority priority;

    /**
     * Set up tasks and storage before each test.
     */
    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>(), new Ui());
        storage = new Storage();
    }

    /**
     * Tests the {@code saveTasks} method, ensuring that tasks are saved to a file
     * and then loaded correctly.
     *
     * @throws IOException    If an I/O error occurs during saving tasks.
     * @throws CruiseyException If an error occurs during loading tasks.
     */
    @Test
    void saveTasks_shouldSaveTasksToFile() throws IOException, CruiseyException {
        taskList.addToDoTask(new ToDo("Finish this project", ui, priority));
        taskList.addDeadlineTask(new Deadline("Test project", "Monday 1200", ui, priority));
        taskList.addEventTask(new Event("Test Event", "2022-12-31", "2023-01-01", ui, priority));

        storage.saveTasks(taskList);

        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(taskList.size(), loadedTasks.size());
        for (int i = 0; i < taskList.size(); i++) {
            assertEquals(taskList.getTask(i).getDescription(), loadedTasks.get(i).getDescription());
            assertEquals(taskList.getTask(i).getType(), loadedTasks.get(i).getType());
            assertEquals(taskList.getTask(i).checkStatus(), loadedTasks.get(i).checkStatus());
        }
        deleteTestFile();
    }

    /**
     * Tests the {@code load} method when the file is empty.
     * It should return an empty task list.
     *
     * @throws CruiseyException If an error occurs during loading tasks.
     * @throws IOException    If an I/O error occurs during saving tasks.
     */
    @Test
    void load_emptyFile_shouldReturnEmptyTaskList() throws CruiseyException, IOException {
        storage.saveTasks(new TaskList(new ArrayList<>(), new Ui()));
        ArrayList<Task> loadedTasks = storage.load();
        assertTrue(loadedTasks.isEmpty());
        deleteTestFile();
    }

    /**
     * Deletes the test file created during testing.
     */
    private void deleteTestFile() {
        File file = new File(Storage.FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}
