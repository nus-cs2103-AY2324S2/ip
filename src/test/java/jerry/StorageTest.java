package jerry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void parseTask_ReturnsToDoTaskCorrectly() {
        String line = "T | 1 | Read book";
        Task task = Storage.parseTask(line);

        assertTrue(task instanceof ToDo);
        assertEquals("Read book", task.getDescription());
        assertTrue(task.isDone());
    }

    @Test
    void parseTask_ReturnsDeadlineTaskCorrectly() {
        String line = "D | 0 | Submit assignment | 2020-12-31";
        Task task = Storage.parseTask(line);

        assertTrue(task instanceof Deadline);
        assertEquals("Submit assignment", task.getDescription());
        assertFalse(task.isDone());
        // Additional assertions to verify the deadline date if necessary
    }

    @Test
    void parseTask_ReturnsEventTaskCorrectly() {
        String line = "E | 1 | Project meeting | 2020-12-30 14:00 | 2020-12-30 16:00";
        Task task = Storage.parseTask(line);

        assertTrue(task instanceof Event);
        assertEquals("Project meeting", task.getDescription());
        assertTrue(task.isDone());
        // Additional assertions to verify event start and end times if necessary
    }

    @Test
    void parseTask_WithInvalidFormat_ReturnsNull() {
        String line = "Invalid format";
        Task task = Storage.parseTask(line);

        assertNull(task);
    }

    @Test
    void parseTask_WithUnknownType_ReturnsNull() {
        String line = "X | 1 | Unknown type task";
        Task task = Storage.parseTask(line);

        assertNull(task);
    }
}
