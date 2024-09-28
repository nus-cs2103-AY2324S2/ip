package linus;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the Storage class.
 */
public class StorageTest {
    // testing the taskToFileString method in Storage.java

    /**
     * Tests whether taskToFileString method correctly converts Todo Task into File String.
     */
    @Test
    public void testTaskToFileStringTodo() {
        Storage storage = new Storage();
        Task todoTask = new Todo("Finish homework", false);

        String expectedOutput = "T | 0 | Finish homework";
        String actualOutput = storage.taskToFileString(todoTask);

        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests whether taskToFileString method correctly converts Deadline Task into File String.
     */
    @Test
    public void testTaskToFileStringDeadline() {
        Storage storage = new Storage();
        LocalDate byDate = LocalDate.parse("2024-02-29");

        Task deadlineTask = new Deadline("Finish Project Linus", byDate, false);

        String expectedOutput = "D | 0 | Finish Project Linus | 2024-02-29";
        String actualOutput = storage.taskToFileString(deadlineTask);

        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests whether taskToFileString method correctly converts Event Task into File String.
     */
    @Test
    public void testTaskToFileStringEvent() {
        Storage storage = new Storage();
        LocalDate fromDate = LocalDate.parse("2024-01-15");
        LocalDate toDate = LocalDate.parse("2024-02-29");
        Task eventTask = new Event("Project Linus", fromDate, toDate, false);

        String expectedOutput = "E | 0 | Project Linus | 2024-01-15 | 2024-02-29";
        String actualOutput = storage.taskToFileString(eventTask);

        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests whether taskToFileString method correctly throws exception for invalid task input.
     */
    // Adapted from AI
    @Test
    public void testTaskToFileStringWrongTask() {
        Storage storage = new Storage();
        Task wrongTask = new Task("Wrong task", false);

        assertThrows(IllegalArgumentException.class, () -> storage.taskToFileString(wrongTask));
    }
}
