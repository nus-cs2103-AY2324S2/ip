/**
 * JUnit test class for the {@link duke.parser.Parser} class.
 */
package duke.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.parser.Parser;
import duke.storage.Storage;

public class ParserTest {
    // Instance variables for the test
    private Storage storage = new Storage();
    private Parser parser = new Parser(storage);

    /**
     * JUnit test method for the {@link duke.parser.Parser#parse(String)} method.
     * Tests the behavior of the parser by checking if the output matches the expected result
     * after adding a new todo task.
     */
    @Test
    public void parserTest() {
        // Get the current size of the task list
        int size = storage.load().size() + 1;
        // Expected output string after parsing the input command
        String expectedOutput = "Got it. I've added this task: \n"
                        + " [T][ ] Hello World\n"
                        + "Now you have " + size + " tasks in the list.";
        // Actual output string from the parser after processing the input command
        String actualOutput = parser.parse("todo Hello World");
        // Assertion to check if the actual output matches the expected output
        assertEquals(expectedOutput, actualOutput);
    }
}

