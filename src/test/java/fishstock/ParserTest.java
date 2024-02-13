package fishstock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getTaskFromIndex_oneInteger_success() throws Exception {
        assertEquals(0, Parser.getIndexFromInput("mark 1"));
        assertEquals(0, Parser.getIndexFromInput("mark 001"));
        assertEquals(99, Parser.getIndexFromInput("unmark 100"));
        assertEquals(-1, Parser.getIndexFromInput("unmark 0"));
    }

    @Test
    public void getTaskFromIndex_multiOrNonInteger_exceptionThrown() {
        try {
            assertEquals(0, Parser.getIndexFromInput("mark 1 2 3 4"));
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number has to be an integer..", e.getMessage());
        }

        try {
            assertEquals(1, Parser.getIndexFromInput("mark 2.0"));
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number has to be an integer..", e.getMessage());
        }

        try {
            assertEquals(0, Parser.getIndexFromInput("mark abc"));
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number has to be an integer..", e.getMessage());
        }
    }

    @Test
    public void getTaskFromIndex_noSpaces_exceptionThrown() {
        try {
            assertEquals(0, Parser.getIndexFromInput("mark"));
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number cannot be empty..", e.getMessage());
        }

        try {
            assertEquals(0, Parser.getIndexFromInput("unmark1"));
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number cannot be empty..", e.getMessage());
        }

        try {
            assertEquals(0, Parser.getIndexFromInput("1"));
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number cannot be empty..", e.getMessage());
        }
    }
}
