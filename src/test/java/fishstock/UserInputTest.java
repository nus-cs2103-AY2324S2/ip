package fishstock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

    @Test
    public void splitByKeywords_oneKeyword() {
        String[] output;

        // Normal use case
        output = new UserInput("some_command description /by date1").splitByKeywords("/by");
        assertEquals("description", output[0]);
        assertEquals("date1", output[1]);
        assertEquals(2, output.length);

        // No spaces (success)
        output = new UserInput("some_command description/bydate1").splitByKeywords("/by");
        assertEquals("description", output[0]);
        assertEquals("date1", output[1]);
        assertEquals(2, output.length);

        // No by-date
        output = new UserInput("some_command description /by").splitByKeywords("/by");
        assertEquals("description", output[0]);
        assertEquals("", output[1]);
        assertEquals(2, output.length);

        // Not found
        output = new UserInput("some_command description by date1").splitByKeywords("/by");
        assertEquals("description by date1", output[0]);
        assertNull(output[1]);
        assertEquals(2, output.length);

        // No description
        output = new UserInput("some_command").splitByKeywords("/by");
        assertEquals("", output[0]);
        assertNull(output[1]);
        assertEquals(2, output.length);
    }

    @Test
    public void splitByKeywords_twoKeyword() {
        String[] output;

        // Normal use case
        output = new UserInput("some_command description /from date1 /to date2").splitByKeywords("/from", "/to");
        assertEquals("description", output[0]);
        assertEquals("date1", output[1]);
        assertEquals("date2", output[2]);
        assertEquals(3, output.length);

        // No spaces (success)
        output = new UserInput("some_command description/fromdate1/todate2").splitByKeywords("/from", "/to");
        assertEquals("description", output[0]);
        assertEquals("date1", output[1]);
        assertEquals("date2", output[2]);
        assertEquals(3, output.length);

        // Wrong keyword order in input
        output = new UserInput("some_command description /to date1 /from date2").splitByKeywords("/from", "/to");
        assertEquals("description /to date1", output[0]);
        assertEquals("date2", output[1]);
        assertNull(output[2]);
        assertEquals(3, output.length);

        // Not found
        output = new UserInput("some_command description /frm date1 / to date2").splitByKeywords("/from", "/to");
        assertEquals("description /frm date1 / to date2", output[0]);
        assertNull(output[1]);
        assertNull(output[2]);
        assertEquals(3, output.length);
    }

    @Test
    public void splitByKeywords_noKeyword() {
        String[] output;

        // Normal use case
        output = new UserInput("some_command super long description").splitByKeywords();
        assertEquals("super long description", output[0]);
        assertEquals(1, output.length);

        // No description
        output = new UserInput("some_command").splitByKeywords();
        assertEquals("", output[0]);
        assertEquals(1, output.length);
    }
}
