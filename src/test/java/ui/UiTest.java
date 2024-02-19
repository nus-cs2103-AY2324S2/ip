package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Task;

/**
 * Tests for the Ui class.
 */
public class UiTest {
    private final Ui ui;

    /**
     * Constructs a new UiTest instance.
     */
    public UiTest() {
        ui = new Ui();
    }

    /**
     * Tests the display of the welcome message.
     * <p>
     * The welcome message is displayed correctly.
     */
    @Test
    public void showWelcomeMessage_correctOutput() {
        String expectedResult = ui.getWelcomeMessage();
        assertEquals("Hello! I'm GeePeeTee\nWhat can I do for you?", expectedResult);
    }

    /**
     * Tests the display of an error message.
     * <p>
     * The error message is displayed correctly.
     */
    @Test
    public void showErrorMessage_correctOutput() {
        String expectedResult = ui.getErrorMessage("Test error message");
        assertEquals("Oops! An error occurred:\nTest error message", expectedResult);
    }

    /**
     * Tests the display of a task message.
     * <p>
     * A task message is displayed correctly.
     */
    @Test
    public void showTaskMessage_correctOutput() {
        Task task = new Task("Test task");
        String expectedResult = ui.getTaskMessage(task);
        assertEquals("[ ] Test task", expectedResult);
    }
}
