package pan.exceptions;

import pan.enums.Commands;
import pan.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
