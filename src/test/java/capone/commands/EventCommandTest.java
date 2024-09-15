package capone.commands;

import capone.Parser;
import capone.Storage;
import capone.TaskList;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import capone.exceptions.InvalidDatePairException;
import capone.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the EventCommand.
 *
 * @author Tay Rui-Jie
 */
public class EventCommandTest {
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
     * Tests the behavior of the EventCommand when no description is provided.
     * Expects an InsufficientArgumentException to be thrown with the correct error message.
     */
    @Test
    public void execute_noDescription_throwsInsufficientArgumentException() {
        Command eventCommand = new EventCommand(Parser.splitInput("event /from 2023-12-11 1800 "
                + "/to 2023-12-12 1900"));

        InsufficientArgumentException exception = assertThrows(InsufficientArgumentException.class, () -> {
            eventCommand.execute(this.taskList, this.ui, this.storage);
        });

        String expectedMsg = "Please enter a description!\nUsage: event [description] "
                + "/from [date] [time] /to [date] [time]";
        assertEquals(expectedMsg, exception.getMessage());
    }

    /**
     * Tests the behavior of the EventCommand when no date/time is provided.
     * Expects an InsufficientArgumentException to be thrown with the correct error message.
     */
    @Test
    public void execute_noDateTime_throwsInsufficientArgumentException() {
        Command eventCommand = new EventCommand(Parser.splitInput("event do iP /from /to"));

        InsufficientArgumentException exception = assertThrows(InsufficientArgumentException.class, () -> {
            eventCommand.execute(this.taskList, this.ui, this.storage);
        });

        String expectedMsg = "Please enter a start and end date!\n"
                + "Usage: event [description] /from [date] [time] /to [date] [time]";
        assertEquals(expectedMsg, exception.getMessage());
    }

    /**
     * Tests the behavior of the EventCommand when /from date and time is after /to date and time.
     * Expects an InvalidDatePairException to be thrown with the correct error message.
     */
    @Test
    public void execute_invalidDatePair_throwsInvalidDatePairException() {
        Command eventCommand = new EventCommand(Parser.splitInput("event do tP /from 2023-12-13 2000 "
                + "/to 2023-12-12 1900"));

        InvalidDatePairException exception = assertThrows(InvalidDatePairException.class, () -> {
            eventCommand.execute(this.taskList, this.ui, this.storage);
        });

        String expectedMsg = "Start date must be before end date!\n"
                + "Usage: event [description] /from [date] [time] /to [date] [time]";
        assertEquals(expectedMsg, exception.getMessage());
    }

    /**
     * Tests the behavior of the EventCommand with valid input.
     * Expects the command to execute successfully and the correct message to be printed.
     */
    @Test
    public void execute_validInput_success() throws CaponeException {
        Command eventCommand = new EventCommand(Parser.splitInput("event do tP /from 2023-12-11 2000 "
                + "/to 2023-12-12 2100"));

        String actualMsg = eventCommand.execute(this.taskList, this.ui, this.storage);

        String expectedMsg = String.format("Got it. I've added this task:\n%s\n"
                + "Now you have %d task(s) in the list.\n", taskList.getLastTask().toString(), taskList.getSize());

        assertEquals(expectedMsg, actualMsg);
    }
}
