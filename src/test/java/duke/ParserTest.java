package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
public class ParserTest {

    @Test
    public void digOutInt_validInt_success() {
        assertEquals(1, new Parser().digOutInt("delete 1"));
        assertEquals(2, new Parser().digOutInt("unmark 2"));
        assertEquals(3, new Parser().digOutInt("mark 3"));
    }

    @Test
    public void decryptInput_string_success() {
        try {
            String[] testing = new String[] {" read book", null, null};
            assertEquals(true, Arrays.equals(testing, new Parser().decryptInput("todo read book")));
        } catch (DukeException e) {
            fail();
        }

        try {
            String[] testing = new String[] {" read book ", " 2020-10-10 18:00", null};
            //assertEquals( testing, new Parser().decryptInput("deadline read book /by 2020-10-10 18:00"));
            assertEquals(true, Arrays.equals(testing, new Parser().decryptInput("deadline read book "
                    + "/by 2020-10-10 18:00")));
        } catch (DukeException e) {
            fail();
        }

        try {
            String[] testing = new String[] {" read book ", " 2020-10-10 18:00 ", " 2020-10-11 18:00"};
            //assertEquals( testing, new Parser().decryptInput("deadline read book /by 2020-10-10 18:00"));
            assertEquals(true, Arrays.equals(testing, new Parser().decryptInput("event read book /from "
                    + "2020-10-10 18:00 /to 2020-10-11 18:00")));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void decryptInput_invalidString_exceptionThrown() {
        try {
            new Parser().decryptInput("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("Empty task fields where applicable are not allowed.\n", e.getMessage());
        }

        try {
            new Parser().decryptInput("deadline /by");
            fail();
        } catch (DukeException e) {
            assertEquals("Empty task fields where applicable are not allowed.\n", e.getMessage());
        }

        try {
            new Parser().decryptInput("event /from 20-10-10 18:00 /to");
            fail();
        } catch (DukeException e) {
            assertEquals("Empty task fields where applicable are not allowed.\n", e.getMessage());
        }
    }
}
