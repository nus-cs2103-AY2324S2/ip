package zack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zack.tasks.Deadline;
import zack.tasks.Event;
import zack.tasks.Task;
import zack.tasks.Todo;
import zack.util.Storage;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage("test_data.txt");
    }

    @Test
    void parseTask_todoFormat_parsedSuccessfully() throws ZackException {
        String input = "T | 0 | read book";
        Task task = storage.parseTask(input);
        assertTrue(task instanceof Todo);
        assertFalse(task.isDone());
        assertEquals("read book", task.getDescription());
    }

    @Test
    void parseTask_deadlineFormat_parsedSuccessfully() throws ZackException {
        String input = "D | 1 | submit assignment | 2022-12-25 2200";
        Task task = storage.parseTask(input);
        assertTrue(task instanceof Deadline);
        assertTrue(task.isDone());
        assertEquals("submit assignment", task.getDescription());
    }

    @Test
    void parseTask_eventFormat_parsedSuccessfully() throws ZackException {
        String input = "E | 0 | project meeting | 2022-12-25 2200 to 2022-12-25 2300";
        Task task = storage.parseTask(input);
        assertTrue(task instanceof Event);
        assertFalse(task.isDone());
        assertEquals("project meeting", task.getDescription());
    }

    @Test
    void parseTask_invalidFormat_exceptionThrown() {
        String input = "T | read book";
        assertThrows(ZackException.class, () -> storage.parseTask(input));
    }

    @Test
    void parseTask_unknownTaskType_exceptionThrown() {
        String input = "? | 0 | play guitar";
        assertThrows(ZackException.class, () -> storage.parseTask(input));
    }
}
