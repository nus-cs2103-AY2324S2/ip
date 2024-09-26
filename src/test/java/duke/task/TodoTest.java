package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The `TodoTest` class represents a test class for the functionality of `Todo` class.
 */
public class TodoTest {
    private static final String SAMPLE_DESCRIPTION = "Project";

    private Todo newTask = new Todo(SAMPLE_DESCRIPTION);

    /**
     * Ensure the "getDescriptionStatus" function outputs the formatted string correctly.
     */
    @Test
    public void getDescriptionStatus_validFormat_returnsFormattedDescriptionStatus() {
        assertEquals("[T][ ] Project",
                newTask.getDescriptionStatus());
    }

}
