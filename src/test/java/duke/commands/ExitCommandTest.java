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
import duke.storage.TaskList;
import duke.ui.Cli;
import duke.ui.Ui;

/**
 * Test cases for methods in the ExitCommand Class
 *
 * @author RyanNgWH
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ExitCommandTest {
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
        taskList = new TaskList(testFile);
        ui = new Cli();
        outContent.reset();
    }

    /**
     * Test print to standard output for successful execute
     */
    @Test
    public void execute_validCommand_success() throws DukeException {
        String expected = "Bye. Hope to see you again soon!\n";

        ExitCommand exitCommand = new ExitCommand();

        exitCommand.execute(taskList, ui);
        assertEquals(expected, outContent.toString());
    }
}
