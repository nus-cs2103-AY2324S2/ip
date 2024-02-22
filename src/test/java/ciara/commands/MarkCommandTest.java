package ciara.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import ciara.Ciara;
import ciara.exceptions.CiaraException;
import ciara.storage.Deadline;
import ciara.storage.Event;
import ciara.storage.TaskList;
import ciara.storage.Todo;
import ciara.ui.Cli;
import ciara.ui.Ui;

/**
 * Test cases for methods in the MarkCommand Class
 *
 * @author RyanNgWH
 */
@TestInstance(Lifecycle.PER_CLASS)
public class MarkCommandTest {
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
     * Sets up streams to allow for System.out testing
     */
    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Restores streams after tests
     */
    @AfterAll
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * Creates testing environment for each test
     */
    @BeforeEach
    public void createEnvironment() throws CiaraException {
        // Create data directory (if required)
        testFile.getParentFile().mkdirs();
        testFile.delete();
        Ciara.setSaveFile(testFile);

        taskList = new TaskList(testFile);
        ui = new Cli();

        taskList.addTask(new Todo("buy lunch"), false);
        taskList.addTask(new Deadline("eat lunch", Instant.ofEpochSecond(1706513963)), false);
        taskList.addTask(new Event("taengoo concert", Instant.ofEpochSecond(1706521160), Instant.ofEpochSecond(
                1706528360)), false);
        taskList.addTask(new Deadline("go school", Instant.ofEpochSecond(1706614760)), false);

        outContent.reset();
    }

    /**
     * Resets testing environment for each test
     */
    @AfterEach
    public void resetEnvironment() {
        testFile.delete();
    }

    /**
     * Tests print to standard output for successful marking of task as completed
     */
    @Test
    public void execute_markTask_success() throws CiaraException {
        String expected = "Nice! I've marked this task as done:\n"
                + "  [E][X] taengoo concert (from: 29-Jan-2024 05:39PM to: 29-Jan-2024 07:39PM)\n";

        MarkCommand markCommand = new MarkCommand(2, true);

        markCommand.execute(taskList, ui);
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests print to standard output for successful unmarking of task as completed
     */
    @Test
    public void execute_unmarkTask_success() throws CiaraException {
        String expected = "Nice! I've marked this task as not done:\n"
                + "  [E][ ] taengoo concert (from: 29-Jan-2024 05:39PM to: 29-Jan-2024 07:39PM)\n";

        MarkCommand markCommand = new MarkCommand(2, false);

        markCommand.execute(taskList, ui);
        assertEquals(expected, outContent.toString());
    }
}
