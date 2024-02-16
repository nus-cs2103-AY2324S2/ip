package duke;

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
    public void testParseTodoCommand() throws DukeException {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test.txt");

        String result = Parser.parse("todo Test Todo", tasks, ui);

        assertEquals("I'm sorry, but I don't understand that command.", result);
    }
}
