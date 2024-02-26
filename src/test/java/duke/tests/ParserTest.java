package duke.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.parser.Parser;
import duke.storage.Storage;

public class ParserTest {
    private Storage storage = new Storage();
    private Parser parser = new Parser(storage);

    @Test
    public void parserTest() {
        int size = storage.load().size() + 1;
        String expectedOutput = "Got it. I've added this task: \n"
                        + " [T][ ] Hello World\n"
                        + "Now you have " + size + " tasks in the list.";
        String actualOutput = parser.parse("todo Hello World");

        assertEquals(expectedOutput, actualOutput);
    }
}
