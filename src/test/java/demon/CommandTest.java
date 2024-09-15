package demon;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

class CommandTest {
    private Command command;
    private TaskList tasks;
    private final String filePath = "test.txt";

    @BeforeEach
    void setUp() throws NoSuchTaskException {
        List<String> storageArray = new ArrayList<>();
        command = new Command(filePath);
        tasks = new TaskList(storageArray);
    }

    @Test
    void testAddDeadlineWithValidInput() {
        String input = "deadline submit assignment /by 12/12/2024 2359";
        assertDoesNotThrow(() -> command.addDeadline(tasks, input));
        assertEquals(1, tasks.getTaskList().size());
        assertTrue(tasks.getTaskList().get(0) instanceof Deadline);
    }

    @Test
    void testAddDeadlineWithInvalidDateFormat() {
        String input = "deadline submit assignment /by 12-12-2024";
        assertThrows(DateTimeParseException.class, () -> command.addDeadline(tasks, input));
    }

    @Test
    void testAddDeadlineDuplicateTask() {
        String input1 = "deadline submit assignment /by 12/12/2024 2359";
        assertDoesNotThrow(() -> command.addDeadline(tasks, input1));
        assertThrows(DuplicateException.class, () -> command.addDeadline(tasks, input1));
    }

    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Failed to delete test file: " + e.getMessage());
        }
    }
}
