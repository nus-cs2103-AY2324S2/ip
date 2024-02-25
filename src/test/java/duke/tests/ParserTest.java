package duke.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.parser.Parser;
import duke.storage.Storage;

public class ParserTest {
    private Parser parser = new Parser(new Storage());

    @Test
    public void parserTest() {
        String expectedOutput = "Got it. I've added this task: \n"
                        + " [T][ ] Hello World\n"
                        + "Now you have 1 tasks in the list.";
        String actualOutput = parser.parse("todo Hello World");

        assertEquals(expectedOutput, actualOutput);
    }
}
