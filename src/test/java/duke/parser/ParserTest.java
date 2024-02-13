package duke.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        parser = new Parser();
        ui = new Ui();
        storage = new Storage();
        todolist = new Tasklist();
        System.setOut(new PrintStream(outContent)); // Redirect System.out to capture the output for testing
    }

    @Test
    public void testMarkTaskAsDone() {
        // Assuming there's already a task in the list
        parser.parseCommand("todo read book", ui, storage, todolist); // Add a task first
        String markInput = "mark 1";
        parser.parseCommand(markInput, ui, storage, todolist);
        assertTrue(outContent.toString().contains("Nice! I've marked this task as done"));
    }

    @Test
    public void testAddTodo() {
        String input = "todo read book";
        parser.parseCommand(input, ui, storage, todolist);
        assertTrue(outContent.toString().contains("added todo: [T][ ] read book"));
    }

    @Test
    public void testInvalidCommand() {
        String input = "dance";
        parser.parseCommand(input, ui, storage, todolist);
        assertTrue(outContent.toString().contains("OOPS!!! I'm sorry, but I don't know what that means"));
    }

    @Test
    public void testEmptyTodo() {
        parser.parseCommand("todo", ui, storage, todolist);
        assertTrue(outContent.toString().contains("OOPS!!! The description of a todo cannot be empty."));
    }
}
