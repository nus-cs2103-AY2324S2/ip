package parser;

import jux.JuxException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParserTest {
    public ParserTest() {

    }
    @Test
    public void parseTodo_inputLenMoreThan5_success() throws Exception {
        assertEquals("lollypop", Parser.parseTodo("todo lollypop"));
    }
    @Test
    public void parseTodo_inputLenLessThan5_failure() {
        try {
            assertEquals("lollypop", Parser.parseTodo("todo"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("PLEASE INSERT DESCRIPTION FOR YOUR TODO", e.getMessage());
        }

    }
    @Test
    public void parseEvent_correctInput_success() throws Exception {
        String[] expected = {"return book ", "2024-05-05 1800", "2024-06-06"};
        assertArrayEquals(expected, Parser.parseEvent("event return book /2024-05-05 1800/2024-06-06"));
    }
    @Test
    public void parseEvent_wrongInput_throwException() {
        try {
            String[] expected = {"return book ", "2024-05-05", "2024-06-06"};
            assertArrayEquals(expected, Parser.parseEvent("event"));
            fail();
        } catch (JuxException e) {
            assertEquals( "PLEASE INSERT DESCRIPTION FOR YOUR EVENT", e.getMessage());
        }
        try {
            String[] expected = {"return book ", "2024-05-05", "2024-06-06"};
            assertArrayEquals(expected, Parser.parseEvent("event return book 2024-05-05 2024-06-06"));
            fail();
        } catch (JuxException e) {
            assertEquals("insert date after task such as event task/monday /sunday", e.getMessage());
        }
    }
}
