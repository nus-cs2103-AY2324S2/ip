package capone.commands;

import capone.Parser;
import capone.Storage;
import capone.TaskList;
import capone.Ui;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private final ByteArrayOutputStream stdoutMsg = new ByteArrayOutputStream();

    @BeforeEach
    public void initializeComponents() {
        this.storage = new Storage("./data/","test.json");
        this.taskList = new TaskList();
        this.ui = new Ui();
        System.setOut(new PrintStream(stdoutMsg));
    }

    @Test
    public void execute_noDescription_exceptionThrown() {
        Command c = new DeadlineCommand(Parser.splitInput("deadline /by 2023-12-12 1800"));

        InsufficientArgumentException e = assertThrows(InsufficientArgumentException.class, () -> {
            c.execute(this.taskList, this.ui, this.storage);
        });

        String expectedMsg = "Insufficient arguments!\nUsage: deadline [description] /by [date]";

        assertEquals(expectedMsg, e.getMessage());
    }

    @Test
    public void execute_noDateTime_exceptionThrown() {
        Command c = new DeadlineCommand(Parser.splitInput("deadline do iP /by"));

        InsufficientArgumentException e = assertThrows(InsufficientArgumentException.class, () -> {
            c.execute(this.taskList, this.ui, this.storage);
        });

        String expectedMsg = "Please enter a date for this deadline task!\n" +
                "Usage: deadline [description] /by [date]";

        assertEquals(expectedMsg, e.getMessage());
    }

    @Test
    public void execute_validInput_success() throws CaponeException {
        Command c = new DeadlineCommand(Parser.splitInput("deadline clean room /by 2024-01-31 1800"));
        c.execute(this.taskList, this.ui, this.storage);

        String expectedMsg = String.format("Got it. I've added this task:\n%s\n" +
                "Now you have %d task(s) in the list.\n", taskList.getLastTask().toString(), taskList.getSize());

        assertEquals(expectedMsg, stdoutMsg.toString());
    }
}
