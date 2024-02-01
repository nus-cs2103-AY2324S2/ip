package duke.storage;

import duke.task.*;
import duke.exception.DukeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    private Storage storage;
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>());
        storage = new Storage();
    }

    @Test
    void saveTasks_shouldSaveTasksToFile() throws IOException, DukeException {
        taskList.addToDoTask(new ToDo("Finish this project"));
        taskList.addDeadlineTask(new Deadline("Test project", "Monday"));
        taskList.addDeadlineTask(new Deadline("Test project", "12/12/2023 1200"));
        taskList.addEventTask(new Event("Test Event", "2022-12-31", "2023-01-01"));

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

    @Test
    void load_emptyFile_shouldReturnEmptyTaskList() throws DukeException, IOException {
        storage.saveTasks(new TaskList());
        ArrayList<Task> loadedTasks = storage.load();
        assertTrue(loadedTasks.isEmpty());
        deleteTestFile();
    }

    private void deleteTestFile() {
        File file = new File(Storage.FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}
