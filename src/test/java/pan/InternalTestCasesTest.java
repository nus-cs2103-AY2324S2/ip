package pan;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import pan.exceptions.InternalTestCases;
import pan.exceptions.InvalidCommandException;

import org.junit.jupiter.api.Test;

public class InternalTestCasesTest {
    @Test
    public void testTestInvalidCommand_valid() {
        assertDoesNotThrow(() -> InternalTestCases.testInvalidCommand("bye"));
    }

    @Test
    public void testTestInvalidCommand_invalid() {
        assertThrows(InvalidCommandException.class, () -> InternalTestCases.testInvalidCommand("rubbish command"));
    }
}
