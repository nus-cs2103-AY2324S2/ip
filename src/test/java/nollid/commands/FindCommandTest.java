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
import nollid.Ui;
import nollid.exceptions.InvalidCommandException;
import nollid.exceptions.NollidException;

public class FindCommandTest {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void initializeComponents() {
        this.storage = new Storage(Storage.TEST_FILEPATH);
        this.tasks = new TaskList(this.storage.load());
        this.ui = new Ui();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_noKeyword_exceptionThrown() {
        NollidException e = assertThrows(NollidException.class, () -> {
            Command c = Parser.parse("find");
            c.execute(this.tasks, this.ui, this.storage);
        });

        String expectedMessage = "Please enter a keyword to search for.\n"
                + FindCommand.USAGE_HINT;
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    public void execute_tooManyKeywords_exceptionThrown() {
        NollidException e = assertThrows(NollidException.class, () -> {
            Command c = Parser.parse("find but with too many keywords");
            c.execute(this.tasks, this.ui, this.storage);
        });

        String expectedMessage = "Please enter only 1 keyword.\n"
                + FindCommand.USAGE_HINT;
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    public void execute_findTaskThatExists_success() throws InvalidCommandException, NollidException {
        Command c = Parser.parse("find deadline");
        c.execute(this.tasks, this.ui, this.storage);

        String expectedMessage = "──────────────────────────────\n"
                + "Here are the matching tasks in your list:\n"
                + "1.[D][X] test deadline (by: 23 Jun 2022 23:33)\n"
                + "──────────────────────────────";
        assertEquals(expectedMessage, outputStreamCaptor.toString().strip());
    }
}
