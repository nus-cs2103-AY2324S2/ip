package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.time.Instant;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import duke.exceptions.DukeException;
import duke.storage.Deadline;
import duke.storage.Event;
import duke.storage.TaskList;
import duke.storage.Todo;
import duke.ui.Cli;
import duke.ui.Ui;

/**
 * Test cases for methods in the ListCommand Class
 *
 * @author RyanNgWH
 */
@TestInstance(Lifecycle.PER_CLASS)
public class ListCommandTest {
    // Streams for testing standard output
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
     * Test print to standard output for successful execute without filter with a
     * populated tasklist
     */
    @Test
    public void execute_noFiltersPopulated_success() throws DukeException {
        String expected = "1.[T][ ] buy lunch\n" + //
                "2.[D][ ] eat lunch (by: 29-Jan-2024 03:39PM)\n" + //
                "3.[E][ ] taengoo concert (from: 29-Jan-2024 05:39PM to: 29-Jan-2024 07:39PM)\n" + //
                "4.[D][ ] go school (by: 30-Jan-2024 07:39PM)\n";

        taskList.addTask(new Todo("buy lunch"));
        taskList.addTask(new Deadline("eat lunch", Instant.ofEpochSecond(1706513963)));
        taskList.addTask(new Event("taengoo concert", Instant.ofEpochSecond(1706521160), Instant.ofEpochSecond(
                1706528360)));
        taskList.addTask(new Deadline("go school", Instant.ofEpochSecond(1706614760)));

        ListCommand listCommand = new ListCommand();

        listCommand.execute(taskList, ui);
        assertEquals(expected, OUT_CONTENT.toString());
    }

    /**
     * Test print to standard output for successful execute with a date filter and a
     * populated tasklist
     */
    @Test
    public void execute_dateFilterPopulated_success() throws DukeException {
        String expected = "1.[D][ ] eat lunch (by: 29-Jan-2024 03:39PM)\n" +
                "2.[E][ ] taengoo concert (from: 29-Jan-2024 05:39PM to: 29-Jan-2024 07:39PM)\n";

        taskList.addTask(new Todo("buy lunch"));
        taskList.addTask(new Deadline("eat lunch", Instant.ofEpochSecond(1706513963)));
        taskList.addTask(new Event("taengoo concert", Instant.ofEpochSecond(1706521160), Instant.ofEpochSecond(
                1706528360)));
        taskList.addTask(new Deadline("go school", Instant.ofEpochSecond(1706614760)));

        ListCommand listCommand = new ListCommand(Instant.ofEpochSecond(1706513963));

        listCommand.execute(taskList, ui);
        assertEquals(expected, OUT_CONTENT.toString());
    }

    /**
     * Test print to standard output for empty result with a date filter and a
     * populated tasklist
     */
    @Test
    public void execute_dateFilterPopulated_empty() throws DukeException {
        String expected = "\n";

        taskList.addTask(new Todo("buy lunch"));
        taskList.addTask(new Deadline("eat lunch", Instant.ofEpochSecond(1706513963)));
        taskList.addTask(new Event("taengoo concert", Instant.ofEpochSecond(1706521160), Instant.ofEpochSecond(
                1706528360)));
        taskList.addTask(new Deadline("go school", Instant.ofEpochSecond(1706614760)));

        ListCommand listCommand = new ListCommand(Instant.ofEpochSecond(1707513963));

        listCommand.execute(taskList, ui);
        assertEquals(expected, OUT_CONTENT.toString());
    }

    /**
     * Test print to standard output for successful execute withou filter and an
     * unpopulated tasklist
     */
    @Test
    public void execute_noFilterUnpopulated_success() throws DukeException {
        String expected = "\n";

        ListCommand listCommand = new ListCommand();

        listCommand.execute(taskList, ui);
        assertEquals(expected, OUT_CONTENT.toString());
    }

    /**
     * Test print to standard output for successful execute withou filter and an
     * unpopulated tasklist
     */
    @Test
    public void execute_dateFilterUnpopulated_success() throws DukeException {
        String expected = "\n";

        ListCommand listCommand = new ListCommand(Instant.ofEpochSecond(1706513963));

        listCommand.execute(taskList, ui);
        assertEquals(expected, OUT_CONTENT.toString());
    }
}
