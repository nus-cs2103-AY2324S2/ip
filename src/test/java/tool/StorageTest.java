package tool;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import task.Task;
import task.Deadline;

public class StorageTest {
    private static final String FILE_PATH = "./data/chronos.txt";

    @Test
    public void convertTaskToSave_success() {
        Storage storage = new Storage(FILE_PATH);
        Task taskToBeConverted = new Deadline("return library book", "2024-03-14 12:00");

        String expectedOutput = "D | 0 | return library book | 2024-03-14 12:00";
        String actualOutput = storage.convertTaskToSave(taskToBeConverted);

        assertEquals(expectedOutput, actualOutput);
    }
}
