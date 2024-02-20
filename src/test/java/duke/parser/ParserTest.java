package duke.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.JosephException;
import duke.storage.Storage;
import duke.task.Tasklist;
import duke.ui.Ui;

/**
 * Represents a test class for Parser
 */
public class ParserTest {
    private Parser parser;
    private Ui ui;
    private Storage storage;
    private Tasklist todolist;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @BeforeEach
    public void setUp() {
        try {
            parser = new Parser();
            ui = new Ui();
            storage = new Storage();
            todolist = new Tasklist();
            System.setOut(new PrintStream(outContent)); // Redirect System.out to capture the output for testing
        } catch (JosephException e) {
            fail();
        }
    }

    @Test
    public void testMarkTaskAsDone() {
        // Assuming there's already a task in the list
        try {
            parser.parseCommand("todo read book", ui, storage, todolist); // Add a task first
            String markInput = "mark 1";
            parser.parseCommand(markInput, ui, storage, todolist);
        } catch (JosephException e) {
            fail();
        }
        assertTrue(outContent.toString().contains("Nice! I've marked this task as done"));
    }

    @Test
    public void testAddTodo() {
        String input = "todo read book";
        try {
            parser.parseCommand(input, ui, storage, todolist);
        } catch (JosephException e) {
            fail();
        }
        assertTrue(outContent.toString().contains("Added: [T][ ] read book"));
    }

    @Test
    public void testInvalidCommand() {
        String input = "dance";
        try {
            parser.parseCommand(input, ui, storage, todolist);
        } catch (JosephException e) {
            assertTrue(e.getMessage().contains("OOPS!!! I'm sorry, but I don't know what that means"));
        }
    }

    @Test
    public void testEmptyTodo() {
        try {
            parser.parseCommand("todo", ui, storage, todolist);
            fail(); // No exception thrown
        } catch (JosephException e) {
            assertTrue(e.getMessage().contains("The description of a task cannot be empty"));
        }
    }
}
