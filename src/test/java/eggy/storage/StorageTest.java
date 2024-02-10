package eggy.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import eggy.exception.EggyException;
import eggy.exception.InvalidTaskTypeException;
import eggy.exception.LoadTasksException;
import eggy.exception.SaveTasksException;
import eggy.task.Event;
import eggy.task.Task;
import eggy.task.TaskList;
import eggy.task.Todo;

public class StorageTest {
    @TempDir
    static Path tempDir;
    private Path tempFile;
    private Storage storage;

    @BeforeEach
    public void setUp() {
        tempFile = tempDir.resolve("temp.txt");
        storage = new Storage(tempFile.toString());
    }

    @AfterEach
    public void tearDown() {
        tempFile.toFile().delete();
    }

    @Test
    public void load_tasksFileExist_success() throws IOException, EggyException {
        Files.write(tempFile, "T | 0 | read book\nD | 0 | return book | 2019-12-02T18:00\n".getBytes());
        List<Task> tasks = storage.load();
        assertTrue(tempFile.toFile().exists(), "File should exist.");
        assertEquals(2, tasks.size());
        assertEquals("[D][ ] return book (by: 02 Dec 2019 06:00 PM)", tasks.get(1).toString());
    }

    @Test
    public void load_tasksFileDoesNotExist_success() throws LoadTasksException, InvalidTaskTypeException {
        tempFile.toFile().delete();
        List<Task> tasks = storage.load();
        assertTrue(tempFile.toFile().exists(), "File should be automatically created.");
        assertEquals(0, tasks.size());
    }

    @Test
    public void load_tasks_exceptionThrown() throws IOException {
        Files.write(tempFile, "A | 0 | read book\nD | 0 | return book | 2019-12-02T18:00\n".getBytes());
        Throwable exception = assertThrows(InvalidTaskTypeException.class, () -> storage.load());
        assertEquals("EGGIES!!! There is an invalid task type in the file :-(", exception.getMessage());
    }

    @Test
    public void save_tasks_success() throws IOException, SaveTasksException {
        TaskList tasks = new TaskList(List.of(new Todo("read book", true), new Event("birthday party",
                LocalDateTime.parse("2023-05-14T17:00"), LocalDateTime.parse("2023-05-14T23:59"))));
        storage.save(tasks);
        assertTrue(tempFile.toFile().exists(), "File should exist.");
        assertEquals(List.of("T | 1 | read book", "E | 0 | birthday party | 2023-05-14T17:00 | 2023-05-14T23:59"),
                Files.readAllLines(tempFile));
    }
}
