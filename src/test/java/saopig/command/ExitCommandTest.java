package saopig.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitCommandTest {
    @Test
    void testIsExit() {
        ExitCommand exitCommand = new ExitCommand();

        // Assert that isExit returns true
        assertTrue(exitCommand.isExit(), "isExit should return true for ExitCommand");
    }
}
