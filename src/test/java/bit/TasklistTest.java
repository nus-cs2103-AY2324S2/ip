package bit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TasklistTest {
    @Test
    public void testAddTo() {
        Tasklist tasklist = new Tasklist();
        String s = "";
        s = attemptExpectingException(tasklist, "todon");
        assertEquals(s, "I have no idea what that means!");
        assertEquals(0, tasklist.getSize());

        s = attemptExpectingException(tasklist, "todo");
        assertEquals("Hmmm, that todo is empty!", s);
        assertEquals(0, tasklist.getSize());

        s = attemptExpectingNoException(tasklist, "todo iP");
        assertEquals("[T][ ] iP", s);

        s = attemptExpectingNoException(tasklist, " 1 todo");
        assertEquals("[T][ ] iP", s);


        s = attemptExpectingNoException(tasklist, "deadline x /by 2020-03-03");
        assertEquals("[D][ ] x  (by: 03 March 2020)", s);

        s = attemptExpectingException(tasklist, "deadline /by 2020-03-04");
        assertEquals("Did you miss something?", s);

        s = attemptExpectingException(tasklist, "deadline x 2020-03-05");
        assertEquals("Did you miss something?", s);

        s = attemptExpectingNoException(tasklist, "deadline y /by Tuesday");
        assertEquals("[D][ ] x  (by: 03 March 2020)", s);

        s = attemptExpectingNoException(tasklist, "event z /from 1 /to 2");
        assertEquals("[E][ ] z  (from:  1  to:  2)", s);

        s = attemptExpectingException(tasklist, "event /from 3 /to 4");
        assertEquals("Missing something?", s);
    }
    private String attemptExpectingException(Tasklist tasklist, String input) {
        try {
            tasklist.addTo(input);
            return "No exception was thrown";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private String attemptExpectingNoException(Tasklist tasklist, String input) {
        try {
            tasklist.addTo(input);
            int size = tasklist.getSize();
            int index = size - 1;
            return tasklist.getTask(index).toString();
        } catch (DukeException e) {
            return "An Exception was thrown";
        }
    }
}
