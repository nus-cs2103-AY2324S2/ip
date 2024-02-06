package data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import core.Ui;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class StorageTest {
    private Path tempFile;
    private Storage storage;
    private Ui mockUi;

    @BeforeEach
    public void setUp(@TempDir Path tempDir) {
        tempFile = tempDir.resolve("testTasks.txt");
        mockUi = mock(Ui.class);
        storage = new Storage(tempFile.toString(), mockUi);
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (Files.exists(tempFile)) {
            Files.delete(tempFile);
        }
    }

    @Test
    public void saveTasks_loadTasks_tasksSavedAndLoaded() {
        ToDo todo = new ToDo("Read book");
        Deadline deadline = new Deadline("Submit assignment", java.time.LocalDate.now());
        Event event = new Event("Team meeting", java.time.LocalDate.now(),
                java.time.LocalDate.now().plusDays(1));
        List<Task> tasksToSave = List.of(todo, deadline, event);
        storage.save(tasksToSave);

        List<Task> loadedTasks = storage.load();
        assertEquals(tasksToSave.size(), loadedTasks.size(), "Loaded tasks should match saved tasks in number.");

        assertTrue(loadedTasks.get(0) instanceof ToDo
                && loadedTasks.get(0).getDescription().equals(todo.getDescription()),
                "First task should be a ToDo with the correct description.");
        assertTrue(loadedTasks.get(1) instanceof Deadline
                && loadedTasks.get(1).getDescription().equals(deadline.getDescription()),
                "Second task should be a Deadline with the correct description.");
        assertTrue(loadedTasks.get(2) instanceof Event
                && loadedTasks.get(2).getDescription().equals(event.getDescription()),
                "Third task should be an Event with the correct description.");
    }

    @Test
    public void loadTasks_noFile_emptyTaskList() {
        // Attempt to load tasks where no file exists
        List<Task> loadedTasks = storage.load();
        assertTrue(loadedTasks.isEmpty(), "Should load an empty list if the file does not exist.");
    }

    @Test
    public void loadTasks_markedTask_loadedTaskCorrectlyMarked() {
        Deadline deadline = new Deadline("Submit assignment", java.time.LocalDate.now());
        deadline.mark();
        List<Task> tasksToSave = List.of(deadline);
        storage.save(tasksToSave);

        List<Task> loadedTasks = storage.load();
        assertEquals(tasksToSave.size(), loadedTasks.size(), "Loaded tasks should match saved tasks in number.");

        assertTrue(loadedTasks.get(0).isDone(), "The loaded task should be marked as done.");
    }
}
