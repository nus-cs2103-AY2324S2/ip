package talktomeorilldie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test for Parser class.
 */
public class ParserTest {

    /**
     * Tests the parse method of Parser.
     */
    @Test
    public void testParseByeCommand() throws TALKTOMEORILLDIEException {
        String userInput = "bye";
        TaskList tasks = new TaskList();
        Ui ui = new Ui();

        String expected = "     Bye. Hope to see you again soon!";
        String actual = Parser.parse(userInput, tasks, ui);

        assertEquals(expected, actual);
    }
}
