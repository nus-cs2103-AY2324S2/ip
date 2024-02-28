package Mitsuki;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EmptyTaskExceptionTest {
    @Test
    public void createTodo_EmptyTaskExceptionThrown() {
        String description = " ";
        String errorMessage = "Empty Task Given";
        try {
            EmptyTaskException.validate(description);
            fail(); // Test should never reach this line
        } catch (EmptyTaskException ex) {
            assertEquals(errorMessage, ex.getMessage());
        }
    }

    @Test
    public void createDeadline_EmptyTaskExceptionThrown() {
        String description = " ";
        String[] tokens = {description};
        String errorMessage = "Empty Task Given";
        try {
            EmptyTaskException.timedValidate(tokens);
            fail(); // Test should never reach this line
        } catch (EmptyTaskException ex) {
            assertEquals(errorMessage, ex.getMessage());
        }
    }
}
