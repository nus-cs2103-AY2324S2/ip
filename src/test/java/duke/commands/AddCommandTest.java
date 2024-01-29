package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import duke.exceptions.DukeException;
import duke.storage.Task;
import duke.storage.TaskList;
import duke.storage.Todo;
import duke.ui.Cli;
import duke.ui.Ui;

/**
 * Test cases for methods in the AddCommand Class
 *
 * @author RyanNgWH
 */
@TestInstance(Lifecycle.PER_CLASS)
public class AddCommandTest {
    // Steams for testing standard output
    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final ByteArrayOutputStream ERR_CONTENT = new ByteArrayOutputStream();
    private final PrintStream ORIGINAL_OUT = System.out;
    private final PrintStream ORIGINAL_ERR = System.err;

    // Test file
    private final File TEST_FILE = new File("data/tasksTest.json");

    // Environment for tests
    private TaskList taskList;
    private Ui ui;

    /**
     * Set up streams to allow for System.out testing
     */
    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
        System.setErr(new PrintStream(ERR_CONTENT));
    }

    /**
     * Restore streams after tests
     */
    @AfterAll
    public void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
        System.setErr(ORIGINAL_ERR);
    }

    /**
     * Create testing environment for each test
     */
    @BeforeEach
    public void createEnvironment() {
        taskList = new TaskList(TEST_FILE);
        ui = new Cli();
        OUT_CONTENT.reset();
    }

    /**
     * Test print to standard output for successful execute
     */
    @Test
    public void execute_validTask_success() throws DukeException {
        String expected = "Got it. I've added this task:\n" +
                "  [T][ ] buy lunch\n" +
                "Now you have 1 tasks in the list.\n";

        Task todo = new Todo("buy lunch");
        AddCommand addCommand = new AddCommand(todo);

        addCommand.execute(taskList, ui);
        assertEquals(expected, OUT_CONTENT.toString());
    }
}
