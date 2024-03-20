package saopig.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ExitCommandTest {
    @Test
    void testIsExit() {
        ExitCommand exitCommand = new ExitCommand();

        // Assert that isExit returns true
        assertTrue(exitCommand.isExit(), "isExit should return true for ExitCommand");
    }
}
