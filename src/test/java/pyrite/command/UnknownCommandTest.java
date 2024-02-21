package pyrite.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import pyrite.StateFile;
import pyrite.TaskList;

/**
 * Test cases for UnknownCommand.
 */
public class UnknownCommandTest {
    // Test cases suggested by Github Copilot
    @Test
    public void execute_success() {
        UnknownCommand unknownCommand = new UnknownCommand("test", "test reason");
        assertTrue(
                unknownCommand
                        .execute(new TaskList(), new StateFile())
                        .equals("test reason\nYour command was: test"));
    }
}
