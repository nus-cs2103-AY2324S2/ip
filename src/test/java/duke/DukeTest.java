package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Class for testing main Duke file.
 */
public class DukeTest {
    @Test
    public void testRun() throws IOException {
        Duke duke = new Duke();

        // Simulate user input
        String userInput = "hi\n";
        String actualOutput = duke.run(userInput);

        // Verify the output
        String expectedOutput = "I do not know what type of task that is!";
        assertEquals(expectedOutput, actualOutput.trim());
    }
}