package fishstock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class UserInputTest {
    @Test
    public void getIndex_oneInteger_success() throws Exception {
        assertEquals(0, new UserInput("mark 1").getIndex());
        assertEquals(0, new UserInput("mark 001").getIndex());
        assertEquals(99, new UserInput("unmark 100").getIndex());
        assertEquals(-1, new UserInput("unmark 0").getIndex());
    }

    @Test
    public void getIndex_multiOrNonInteger_exceptionThrown() {
        try {
            assertEquals(0, new UserInput("mark 1 2 3 4").getIndex());
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number has to be an integer..", e.getMessage());
        }

        try {
            assertEquals(1, new UserInput("mark 2.0").getIndex());
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number has to be an integer..", e.getMessage());
        }

        try {
            assertEquals(0, new UserInput("mark abc").getIndex());
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number has to be an integer..", e.getMessage());
        }
    }

    @Test
    public void getIndex_noSpaces_exceptionThrown() {
        try {
            assertEquals(0, new UserInput("mark").getIndex());
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number cannot be empty..", e.getMessage());
        }

        try {
            assertEquals(0, new UserInput("unmark1").getIndex());
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number cannot be empty..", e.getMessage());
        }

        try {
            assertEquals(0, new UserInput("1").getIndex());
            fail();
        } catch (Exception e) {
            assertEquals("OH NOSE! Task number cannot be empty..", e.getMessage());
        }
    }
}
