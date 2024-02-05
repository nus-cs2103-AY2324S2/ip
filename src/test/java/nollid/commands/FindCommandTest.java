package nollid.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nollid.Parser;
import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.NollidException;

/**
 * A set of tests for the FindCommand class.
 */
public class FindCommandTest {
    /**
     * The TaskList object used for testing.
     */
    private TaskList tasks;

    /**
     * The Storage object used for testing.
     */
    private Storage storage;

    /**
     * The ByteArrayOutputStream for capturing system output during tests.
     */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    /**
     * Initializes the components before each test.
     */
    @BeforeEach
    public void initializeComponents() {
        this.storage = new Storage(Storage.TEST_FILEPATH);
        this.tasks = new TaskList(this.storage.load());
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Tests the execute method of FindCommand when no keyword is provided.
     */
    @Test
    public void execute_noKeyword_exceptionThrown() {
        NollidException e = assertThrows(NollidException.class, () -> {
            Command c = Parser.parse("find");
            c.execute(this.tasks, this.storage);
        });

        String expectedMessage = "Please enter a keyword to search for.\n"
                + FindCommand.USAGE_HINT;
        assertEquals(expectedMessage, e.getMessage());
    }

    /**
     * Tests the execute method of FindCommand when too many keywords are provided.
     */
    @Test
    public void execute_tooManyKeywords_exceptionThrown() {
        NollidException e = assertThrows(NollidException.class, () -> {
            Command c = Parser.parse("find but with too many keywords");
            c.execute(this.tasks, this.storage);
        });

        String expectedMessage = "Please enter only 1 keyword.\n"
                + FindCommand.USAGE_HINT;
        assertEquals(expectedMessage, e.getMessage());
    }

    /**
     * Tests the execute method of FindCommand when finding a task that exists.
     */
    @Test
    public void execute_findTaskThatExists_success() throws NollidException {
        Command c = Parser.parse("find deadline");
        String actualMessage = c.execute(this.tasks, this.storage);

        String expectedMessage = "Here are the matching tasks in your list:\n"
                + "1.[D][ ] test deadline (by: 22 Feb 2222 22:22)";
        assertEquals(expectedMessage, actualMessage);
    }
}
