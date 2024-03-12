package cvb.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskParserTest {

    @Test
    void parseTaskFromLineTodo_validInput_taskParsedSuccessfully() throws IllegalArgumentException {
        String line = "T | 0 | Read a book";
        Task task = TaskParser.parse(line);
        assertTrue(task instanceof ToDo);
        assertFalse(task.getIsDone());
        assertEquals("Read a book", task.getDescription());
    }

    @Test
    void parseTaskFromLineDeadline_validInput_taskParsedSuccessfully() throws IllegalArgumentException {
        String line = "D | 1 | Finish project | 2024-02-01";
        Task task = TaskParser.parse(line);
        assertTrue(task instanceof Deadline);
        assertTrue(task.getIsDone());
        assertEquals("Finish project", task.getDescription());
    }

    @Test
    void parseTaskFromLineEvent_validInput_taskParsedSuccessfully() throws IllegalArgumentException {
        String line = "E | 0 | Team meeting | 2024-02-01 | 2024-02-02";
        Task task = TaskParser.parse(line);
        assertTrue(task instanceof Event);
        assertFalse(task.getIsDone());
        assertEquals("Team meeting", task.getDescription());
    }

    @Test
    void parseTaskFromLineInvalidFormat_invalidFormat_exceptionThrown() {
        String line = "Invalid Format";
        assertThrows(IllegalArgumentException.class, () -> TaskParser.parse(line));
    }
}
