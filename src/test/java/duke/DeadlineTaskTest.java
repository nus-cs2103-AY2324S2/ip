package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTaskTest {
    @Test
    public void testStringConversion() {
        DeadlineTask deadline = null;
        try {
            deadline = new DeadlineTask("return book", "2020-12-12");
            assertEquals("[D][ ] return book (by: Dec 12 2020)", deadline.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFileConversion() {
        DeadlineTask deadline = null;
        try {
            deadline = new DeadlineTask("return book", "2020-12-12");
            assertEquals("D | 0 | return book | 2020-12-12", deadline.toFileString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testInvalidDate() {
        try {
            DeadlineTask deadline = new DeadlineTask("return book", "2020-12-32");
            fail();
        } catch (DukeException e) {
            assertEquals("can you follow the format yyyy-mm-dd pls", e.getMessage());
        }
    }

    @Test
    public void testEmptyName() {
        try {
            DeadlineTask deadline = new DeadlineTask("", "2020-12-12");
            fail();
        } catch (DukeException e) {
            assertEquals("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21", e.getMessage());
        }
    }

    @Test
    public void testEmptyDeadline() {
        try {
            DeadlineTask deadline = new DeadlineTask("return book", "");
            fail();
        } catch (DukeException e) {
            assertEquals("bro this task needs a deadline bro", e.getMessage());
        }
    }

    @Test
    public void testEmptyNameAndDeadline() {
        try {
            DeadlineTask deadline = new DeadlineTask("", "");
            fail();
        } catch (DukeException e) {
            assertEquals("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21", e.getMessage());
        }
    }

    @Test
    public void testEmptyNameAndInvalidDeadline() {
        try {
            DeadlineTask deadline = new DeadlineTask("", "2020-12-32");
            fail();
        } catch (DukeException e) {
            assertEquals("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21", e.getMessage());
        }
    }

    @Test
    public void testMarkAsDone() {
        DeadlineTask deadline = null;
        try {
            deadline = new DeadlineTask("return book", "2020-12-12");
            deadline.markDone(true);
            assertEquals("[D][X] return book (by: Dec 12 2020)", deadline.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMarkAsUndone() {
        DeadlineTask deadline = null;
        try {
            deadline = new DeadlineTask("return book", "2020-12-12");
            deadline.markDone(true);
            deadline.markDone(false);
            assertEquals("[D][ ] return book (by: Dec 12 2020)", deadline.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMarkAsDoneAndFileConversion() {
        DeadlineTask deadline = null;
        try {
            deadline = new DeadlineTask("return book", "2020-12-12");
            deadline.markDone(true);
            assertEquals("D | 1 | return book | 2020-12-12", deadline.toFileString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMarkAsUndoneAndFileConversion() {
        DeadlineTask deadline = null;
        try {
            deadline = new DeadlineTask("return book", "2020-12-12");
            deadline.markDone(true);
            deadline.markDone(false);
            assertEquals("D | 0 | return book | 2020-12-12", deadline.toFileString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
