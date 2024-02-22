package ciara.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import ciara.Ciara;
import ciara.exceptions.CiaraException;
import ciara.storage.Task;
import ciara.storage.TaskList;
import ciara.storage.Todo;
import ciara.ui.Cli;
import ciara.ui.Ui;

/**
 * Test cases for methods in the DeleteCommand Class
 *
 * @author RyanNgWH
 */
@TestInstance(Lifecycle.PER_CLASS)
public class DeleteCommandTest {
    // Streams for testing standard output
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    // Test file
    private final File testFile = new File("data/tasksTest.json");

    // Environment for tests
    private TaskList taskList;
    private Ui ui;

    /**
     * Set up streams to allow for System.out testing
     */
    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Restore streams after tests
     */
    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Create testing environment for each test
     */
    @BeforeEach
    public void createEnvironment() {
        // Create data directory (if required)
        testFile.getParentFile().mkdirs();
        testFile.delete();
        Ciara.setSaveFile(testFile);

        taskList = new TaskList(testFile);
        ui = new Cli();
        outContent.reset();
    }

    /**
     * Reset testing environment for each test
     */
    @AfterEach
    public void resetEnvironment() {
        testFile.delete();
    }

    /**
     * Test print to standard output for successful execute
     */
    @Test
    public void execute_validTask_success() throws CiaraException {
        String expected = "Noted. I've removed this task:\n"
                + "  [T][ ] buy lunch\n"
                + "Now you have 0 tasks in the list.\n";

        Task todo = new Todo("buy lunch");
        taskList.addTask(todo, false);
        DeleteCommand deleteCommand = new DeleteCommand(0, false);

        deleteCommand.execute(taskList, ui);
        assertEquals(expected, outContent.toString());
    }
}
