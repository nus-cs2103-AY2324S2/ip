package nollid.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nollid.Parser;
import nollid.Storage;
import nollid.TaskList;
import nollid.exceptions.EmptyDescriptionException;
import nollid.exceptions.InvalidCommandException;
import nollid.exceptions.MissingTagsException;
import nollid.exceptions.NollidException;

public class TodoCommandTest {
    /**
     * The TaskList object used for testing.
     */
    private TaskList tasks;

    /**
     * The Storage object used for testing.
     */
    private Storage storage;

    @BeforeEach
    public void initializeComponents() throws IOException {
        Path testFile = Paths.get("./data/tests/commands/todocommand_test.json");
        Files.deleteIfExists(testFile);
        this.storage = new Storage(testFile);
        this.tasks = new TaskList(this.storage.load());
    }

    @AfterEach
    public void cleanup() throws IOException {
        Path testFile = Paths.get("./data/tests/commands/todocommand_test.json");
        Files.deleteIfExists(testFile);
    }

    @Test
    public void execute_noDescription_exceptionThrown() throws InvalidCommandException {
        Command c = Parser.parse("todo");

        assertThrows(EmptyDescriptionException.class, () -> {
            c.execute(tasks, storage);
        });
    }

    @Test
    public void execute_tagsOnly_exceptionThrown() throws InvalidCommandException {
        Command c = Parser.parse("todo /tags a,b,c");

        assertThrows(EmptyDescriptionException.class, () -> {
            c.execute(tasks, storage);
        });
    }

    @Test
    public void execute_optionWithNoDescription_exceptionThrown() throws InvalidCommandException {
        Command c = Parser.parse("todo /a");

        assertThrows(EmptyDescriptionException.class, () -> {
            c.execute(tasks, storage);
        });
    }

    @Test
    public void execute_tagOptionButNoTags_exceptionThrown() throws InvalidCommandException {
        Command c = Parser.parse("todo test /tags");

        assertThrows(MissingTagsException.class, () -> {
            c.execute(tasks, storage);
        });
    }

    @Test
    public void execute_expectedUsage_success() throws NollidException {
        Command c = Parser.parse("todo test /tags a,b,c");

        String actual = c.execute(tasks, storage);
        String expected = "Alright, added:\n"
                + "\t[T][ ] test\n"
                + "Tags: a, b, c\n"
                + "You now have 1 task in your list.";

        assertEquals(expected, actual);
    }
}
