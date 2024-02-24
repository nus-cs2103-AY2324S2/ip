package bit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TasklistTest {
/* Test method addTo() */
    @Test
    public void testAddTo() {
        Tasklist tasklist = new Tasklist();
        String s = "";
        try {
            tasklist.addTo("todon");
        } catch (DukeException e) {
            s = e.getMessage();
        }
        assertEquals(s, "I have no idea what that means!");
        assertEquals(0, tasklist.getSize());

        try {
            tasklist.addTo("todo");
        } catch (DukeException e) {
            s = e.getMessage();
        }
        assertEquals(s, "Hmmm, that todo is empty!");
        assertEquals(0, tasklist.getSize());

        try {
            tasklist.addTo("todo iP");
        } catch (DukeException e) {

        }
        assertEquals(1, tasklist.getSize());
        assertEquals("[T][ ] iP", tasklist.getTask(0).toString());

        try {
            tasklist.addTo(" 1 todo");
        } catch (DukeException e) {
            assertEquals("I have no idea what that means!", e.getMessage());
        }
        assertEquals(tasklist.getSize(), 1);

        try {
            tasklist.addTo("deadline x /by 2020-03-03");
        } catch (DukeException e) {

        }
        assertEquals("[D][ ] x  (by: 03 March 2020)", tasklist.getTask(1).toString());

        try {
            tasklist.addTo("deadline /by 2020-03-04");
        } catch (DukeException e) {
            assertEquals("Did you miss something?", e.getMessage());
        }

        try {
            tasklist.addTo("deadline x 2020-03-05");
        } catch (DukeException e) {
            assertEquals("Did you miss something?", e.getMessage());
        }

        try {
            tasklist.addTo("deadline y /by Tuesday");
        } catch (DukeException e) {
            assertEquals("", e.getMessage());
        }

        try {
            tasklist.addTo("event z /from 1 /to 2");
        } catch (DukeException e) {

        }
        assertEquals("[E][ ] z  (from:  1  to:  2)", tasklist.getTask(2).toString());

        try {
            tasklist.addTo("event /from 3 /to 4");
        } catch (DukeException e) {
            assertEquals("Missing something?", e.getMessage());
        }
    }
}
