package capone.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import capone.Parser;
import capone.Storage;
import capone.TaskList;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import capone.ui.Ui;

/**
 * Test class for the DeadlineCommand.
 *
 * @author Tay Rui-Jie
 */
public class DeadlineCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Initializes the components needed for each test.
     */
    @BeforeEach
    public void initializeComponents() {
        this.storage = new Storage("./data/", "test.json");
        this.taskList = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Tests the behavior of the DeadlineCommand when no description is provided.
     * Expects an InsufficientArgumentException to be thrown with the correct error message.
     */
    @Test
    public void execute_noDescription_throwsInsufficientArgumentException() {
        Command deadlineCommand = new DeadlineCommand(Parser.splitInput("deadline /by 2023-12-12 1800"));

        InsufficientArgumentException exception = assertThrows(InsufficientArgumentException.class, () -> {
            deadlineCommand.execute(this.taskList, this.ui, this.storage);
        });

        String expectedMsg = "Insufficient arguments!\nUsage: deadline [description] /by [date]";
        assertEquals(expectedMsg, exception.getMessage());
    }

    /**
     * Tests the behavior of the DeadlineCommand when no date/time is provided.
     * Expects an InsufficientArgumentException to be thrown with the correct error message.
     */
    @Test
    public void execute_noDateTime_throwsInsufficientArgumentException() {
        Command deadlineCommand = new DeadlineCommand(Parser.splitInput("deadline do iP /by"));

        InsufficientArgumentException exception = assertThrows(InsufficientArgumentException.class, () -> {
            deadlineCommand.execute(this.taskList, this.ui, this.storage);
        });

        String expectedMsg = "Please enter a date for this deadline task!\n"
                + "Usage: deadline [description] /by [date]";
        assertEquals(expectedMsg, exception.getMessage());
    }

    /**
     * Tests the behavior of the DeadlineCommand with valid input.
     * Expects the command to execute successfully and the correct message to be printed.
     */
    @Test
    public void execute_validInput_success() throws CaponeException {
        Command deadlineCommand = new DeadlineCommand(Parser.splitInput("deadline clean room /by 2024-01-31 1800"));

        String actualMsg = deadlineCommand.execute(this.taskList, this.ui, this.storage);

        String expectedMsg = String.format("Got it. I've added this task:\n%s\n"
                + "Now you have %d task(s) in the list.\n", taskList.getLastTask().toString(), taskList.getSize());

        assertEquals(expectedMsg, actualMsg);

    }
}
