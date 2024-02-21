package julia;
import julia.task.Task;
import julia.task.Todo;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

/**
 * This class contains JUnit test cases to verify the functionality of the Ui class.
 */

public class UiTest {

    @Test
    public void showWelcomeMessage_outputContainsWelcomeMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        ui.showWelcomeMessage();
        assertTrue(outContent.toString().contains("Hello! I'm Duke. What can I do for you?"));
    }








    @Test
    public void hasNextInt_mockUserInputIsNotInt_returnsFalse() {
        Ui ui = new Ui();
        String mockInput = "abc";
        System.setIn(new java.io.ByteArrayInputStream(mockInput.getBytes()));
        assertFalse(ui.hasNextInt());
    }

    @Test
    public void showGoodbyeMessage_outputContainsGoodbyeMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        ui.showGoodbyeMessage();
        assertTrue(outContent.toString().contains("Bye! Hope to see you again soon."));
    }

    @Test
    public void showError_outputContainsErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        ui.showError("Test error message");
        assertTrue(outContent.toString().contains("Error: Test error message"));
    }

    @Test
    public void showLoadingError_outputContainsLoadingErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        ui.showLoadingError();
        assertTrue(outContent.toString().contains("Error loading tasks from file. Starting with an empty task list."));
    }

    @Test
    public void markedMessage_outputContainsMarkedMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        Task task = new Todo("Sample Task");
        ui.markedMessage(task);
        assertTrue(outContent.toString().contains("Nice! I've marked this task as done:"));
        assertTrue(outContent.toString().contains("\t " + task));
    }

    @Test
    public void showMessage_outputContainsMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        ui.showMessage("Test message");
        assertTrue(outContent.toString().contains("Test message"));
    }
}
