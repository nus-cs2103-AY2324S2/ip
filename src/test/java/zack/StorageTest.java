package zack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zack.tasks.Deadline;
import zack.tasks.Event;
import zack.tasks.Task;
import zack.tasks.Todo;
import zack.util.Storage;

class StorageTest {

    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage("test_data.txt");
    }

    @Test
    void parseTask_todoFormat_parsedSuccessfully() throws ZackException {
        String input = "T | 0 | read book | 2019-01-21T05:47:08.644";
        Task task = storage.parseStringToTask(input);
        assertInstanceOf(Todo.class, task);
        assertFalse(task.isDone());
        assertEquals("read book", task.getDescription());
    }

    @Test
    void parseTask_deadlineFormat_parsedSuccessfully() throws ZackException {
        String input = "D | 1 | submit assignment | 2022-12-25 2200 | 2019-01-21T05:47:08.644";
        Task task = storage.parseStringToTask(input);
        assertInstanceOf(Deadline.class, task);
        assertTrue(task.isDone());
        assertEquals("submit assignment", task.getDescription());
    }

    @Test
    void parseTask_eventFormat_parsedSuccessfully() throws ZackException {
        String input = "E | 0 | project meeting | 2022-12-25 2200 to 2022-12-25 2300"
                + " | 2019-01-21T05:47:08.644";
        Task task = storage.parseStringToTask(input);
        assertInstanceOf(Event.class, task);
        assertFalse(task.isDone());
        assertEquals("project meeting", task.getDescription());
    }

    @Test
    void parseTask_invalidFormat_exceptionThrown() {
        String input = "T | read book";
        assertThrows(ZackException.class, () -> storage.parseStringToTask(input));
    }



    @Test
    void parseTask_unknownTaskType_exceptionThrown() {
        String input = "? | 0 | play guitar";
        assertThrows(ZackException.class, () -> storage.parseStringToTask(input));
    }
}
