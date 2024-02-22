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
 * Test cases for methods in the ListCommand Class
 *
 * @author RyanNgWH
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ListCommandTest {
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
     * Resets testing environment for each test
     */
    @AfterEach
    public void resetEnvironment() {
        testFile.delete();
    }

    /**
     * Tests print to standard output for successful execute without filter with a
     * populated tasklist
     */
    @Test
    public void execute_noFiltersPopulated_success() throws CiaraException {
        String expected = "1.[T][ ] buy lunch\n"
                + "2.[D][ ] eat lunch (by: 29-Jan-2024 03:39PM)\n"
                + "3.[E][ ] taengoo concert (from: 29-Jan-2024 05:39PM to: 29-Jan-2024 07:39PM)\n"
                + "4.[D][ ] go school (by: 30-Jan-2024 07:39PM)\n";

        taskList.addTask(new Todo("buy lunch"), false);
        taskList.addTask(new Deadline("eat lunch", Instant.ofEpochSecond(1706513963)), false);
        taskList.addTask(new Event("taengoo concert", Instant.ofEpochSecond(1706521160), Instant.ofEpochSecond(
                1706528360)), false);
        taskList.addTask(new Deadline("go school", Instant.ofEpochSecond(1706614760)), false);

        ListCommand listCommand = new ListCommand(false);

        listCommand.execute(taskList, ui);
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests print to standard output for successful execute with a date filter and
     * a
     * populated tasklist
     */
    @Test
    public void execute_dateFilterPopulated_success() throws CiaraException {
        String expected = "1.[D][ ] eat lunch (by: 29-Jan-2024 03:39PM)\n"
                + "2.[E][ ] taengoo concert (from: 29-Jan-2024 05:39PM to: 29-Jan-2024 07:39PM)\n";

        taskList.addTask(new Todo("buy lunch"), false);
        taskList.addTask(new Deadline("eat lunch", Instant.ofEpochSecond(1706513963)), false);
        taskList.addTask(new Event("taengoo concert", Instant.ofEpochSecond(1706521160), Instant.ofEpochSecond(
                1706528360)), false);
        taskList.addTask(new Deadline("go school", Instant.ofEpochSecond(1706614760)), false);

        ListCommand listCommand = new ListCommand(Instant.ofEpochSecond(1706513963), false);

        listCommand.execute(taskList, ui);
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests print to standard output for empty result with a date filter and a
     * populated tasklist
     */
    @Test
    public void execute_dateFilterPopulated_empty() throws CiaraException {
        String expected = "\n";

        taskList.addTask(new Todo("buy lunch"), false);
        taskList.addTask(new Deadline("eat lunch", Instant.ofEpochSecond(1706513963)), false);
        taskList.addTask(new Event("taengoo concert", Instant.ofEpochSecond(1706521160), Instant.ofEpochSecond(
                1706528360)), false);
        taskList.addTask(new Deadline("go school", Instant.ofEpochSecond(1706614760)), false);

        ListCommand listCommand = new ListCommand(Instant.ofEpochSecond(1707513963), false);

        listCommand.execute(taskList, ui);
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests print to standard output for successful execute without filter and an
     * unpopulated tasklist
     */
    @Test
    public void execute_noFilterUnpopulated_success() throws CiaraException {
        String expected = "\n";

        ListCommand listCommand = new ListCommand(false);

        listCommand.execute(taskList, ui);
        assertEquals(expected, outContent.toString());
    }

    /**
     * Tests print to standard output for successful execute with filter and an
     * unpopulated tasklist
     */
    @Test
    public void execute_dateFilterUnpopulated_success() throws CiaraException {
        String expected = "\n";

        ListCommand listCommand = new ListCommand(Instant.ofEpochSecond(1706513963), false);

        listCommand.execute(taskList, ui);
        assertEquals(expected, outContent.toString());
    }
}
