package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for Ui
 */
public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        ui = new Ui(new ByteArrayInputStream("".getBytes()), System.out);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintMessage() {
        String message = "This is a test message.";
        ui.printMessage(message);
        String expectedOutput = "\t__________________________________________\r\n\t"
                + message + "\r\n\t__________________________________________\r\n";
        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testPrintWelcomeMessage() {
        ui.printWelcomeMessage();
        String welcomeMessage1 = "Hello! I'm JOSEPH JOSHTUR!!!";
        String welcomeMessage2 = "What can I do for you?";
        assertTrue(outContent.toString().contains(welcomeMessage1));
        assertTrue(outContent.toString().contains(welcomeMessage2));
    }

    @Test
    public void testPrintByeMessage() {
        ui.printByeMessage();
        String byeMessage = "Bye. Hope to see you again soon!";
        assertTrue(outContent.toString().contains(byeMessage));
    }

    @Test
    public void testGetCommand() {
        String simulatedUserInput = "test command\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        ui = new Ui(System.in, System.out);

        String command = ui.getCommand();

        assertEquals("test command", command, "getCommand should return the input trimmed and normalized.");
    }

    @Test
    public void testGetCommandWithLeadingAndTrailingSpaces() {
        String simulatedUserInput = "   test command   \n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        ui = new Ui(System.in, System.out);

        String command = ui.getCommand();

        assertEquals("test command", command, "getCommand should trim leading and trailing spaces.");
    }

    @Test
    public void testGetCommandWithEmptyInput() {
        // Providing multiple lines of input where the first one is empty
        // to simulate pressing enter without typing anything and then providing valid input
        String simulatedUserInput = "\nvalid command\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        ui = new Ui(System.in, System.out);

        String command = ui.getCommand();

        assertEquals("valid command", command, "getCommand should ignore empty lines and wait for valid input.");
    }
}
