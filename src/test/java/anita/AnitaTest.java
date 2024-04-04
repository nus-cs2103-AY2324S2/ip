package anita;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AnitaTest {

    @Test
    public void allocateTask_invalidCommand_exceptionThrown() throws Exception {
        try {
            new Anita().allocateTask("fly", "i cannot fly");
        } catch (Exception e) {
            assertEquals("Please enter a valid command.", e.getMessage());
        }

        try {
            new Anita().allocateTask("", "");
        } catch (Exception e) {
            assertEquals("Please enter a valid command.", e.getMessage());
        }
    }

    @Test
    public void allocateTask_invalidDescription_exceptionThrown() throws Exception {
        try {
            new Anita().allocateTask("todo", "");
        } catch (Exception e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }

        try {
            new Anita().allocateTask("event", "");
        } catch (Exception e) {
            assertEquals("The description of an event cannot be empty.", e.getMessage());
        }

        try {
            new Anita().allocateTask("deadline", "");
        } catch (Exception e) {
            assertEquals("The description of a deadline cannot be empty.", e.getMessage());
        }
    }
}
