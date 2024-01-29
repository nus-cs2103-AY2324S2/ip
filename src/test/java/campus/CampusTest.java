package campus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CampusTest {
    @Test
    public void campusBotInitialises() {
        // Redirect System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the method
        String filePath = "src/test/java/campus/dataTest.txt";
        Campus campusBot = new Campus(filePath);
        campusBot.run();

        // Set up simulated input
        String simulatedInput = "bye";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Capture and trim the console output
        String consoleOutput = outContent.toString();

        // Restore System.out
        System.setOut(System.out);

        String expected = "------------------------------\n" +
                "Hello! I'm Campus\n" +
                "What can I do for you?\n" +
                "\n" +
                "------------------------------\n\n";

        // Assert the expected output
        assertEquals(expected, consoleOutput);
    }
}
