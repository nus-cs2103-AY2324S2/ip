package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The `TaskTest` class represents a test class for the functionality of `Task` class.
 */
public class TaskTest {
    private static final String SAMPLE_DESCRIPTION = "Project";
    private static final String SAMPLE_TAG = "tag";

    private Todo newTask = new Todo(SAMPLE_DESCRIPTION);

    /**
     * Ensure the "getDescriptionStatus" function outputs the formatted string correctly.
     */
    @Test
    public void getDescriptionStatus_validFormat_returnsFormattedDescriptionStatus() {
        newTask.addTag(SAMPLE_TAG);
        assertEquals("[T][ ] Project #tag",
                newTask.getDescriptionStatus());
    }

}
