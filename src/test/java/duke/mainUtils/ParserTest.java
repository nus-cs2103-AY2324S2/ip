package duke.mainUtils;

import duke.exceptions.InvalidDateException;
import duke.exceptions.StorageException;
import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void testParseSaveFile_ValidToDoTask() throws StorageException, InvalidDateException {
        String taskStringData = "T | [ ] | Task Description";
        Task expectedTask = new ToDoTask("Task Description");

        Task parsedTask = Parser.parseSaveFile(taskStringData);

        assertEquals(expectedTask.toString(), parsedTask.toString());
    }

    @Test
    void testParseSaveFile_ValidDeadlineTask() throws StorageException, InvalidDateException {
        String taskStringData = "D | [ ] | Task Description | (by: 2022-01-30)";
        Task expectedTask = new DeadlineTask("Task Description", LocalDate.of(2022, 1, 30));

        Task parsedTask = Parser.parseSaveFile(taskStringData);

        Assertions.assertEquals(expectedTask.toString(), parsedTask.toString());
    }

    @Test
    void testParseSaveFile_ValidEventTask() throws StorageException, InvalidDateException {
        String taskStringData = "E | [ ] | Task Description | (from: 2022-01-30 to: 2022-01-31)";
        Task expectedTask = new EventTask("Task Description", LocalDate.of(2022, 1, 30), LocalDate.of(2022, 1, 31));

        Task parsedTask = Parser.parseSaveFile(taskStringData);

        assertEquals(expectedTask.toString(), parsedTask.toString());
    }

    @Test
    void testParseSaveFile_InvalidTaskData() {
        String invalidTaskStringData = "Invalid Task Data";

        assertThrows(StorageException.class, () -> Parser.parseSaveFile(invalidTaskStringData));
    }

    @Test
    void testParseDateTime_ValidFormat() throws InvalidDateException {
        String validDateString = "2022-01-30";
        LocalDate expectedDate = LocalDate.of(2022, 1, 30);

        LocalDate parsedDate = Parser.parseDateTime(validDateString);

        assertEquals(expectedDate, parsedDate);
    }

    @Test
    void testParseDateTime_InvalidFormat() {
        String invalidDateString = "2022/01/30";

        assertThrows(InvalidDateException.class, () -> Parser.parseDateTime(invalidDateString));
    }

}
