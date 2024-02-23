package junjie.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DateTimeException;

import org.junit.jupiter.api.Test;

import junjie.exceptions.InvalidArgumentException;

public class DeadlineTaskTest {
    private static final String INVALID_DATE_FORMAT = "eh the date/time format is wrong la, must be yyyy-mm-dd";
    private static final String INVALID_NAME = "oi the task needs a name la \uD83D\uDE21\uD83D\uDE21";
    private static final String INVALID_DEADLINE = "bro this task needs a deadline bro";


    @Test
    public void testStringConversion() throws InvalidArgumentException, DateTimeException {
        DeadlineTask deadline = new DeadlineTask("return book", "2020-12-12");
        assertEquals("[D][ ] return book (by: Dec 12 2020)", deadline.toString());
    }

    @Test
    public void testFileConversion() throws InvalidArgumentException, DateTimeException {
        DeadlineTask deadline = new DeadlineTask("return book", "2020-12-12");
        assertEquals("D | 0 | return book | 2020-12-12 | ", deadline.toFileString());
    }

    @Test
    public void testInvalidDate() throws InvalidArgumentException, DateTimeException {
        try {
            DeadlineTask deadline = new DeadlineTask("return book", "2020-12-32");
            fail();
        } catch (DateTimeException e) {
            assertEquals(INVALID_DATE_FORMAT, e.getMessage());
        }
    }

    @Test
    public void testEmptyName() throws InvalidArgumentException, DateTimeException {
        try {
            DeadlineTask deadline = new DeadlineTask("", "2020-12-12");
            fail();
        } catch (InvalidArgumentException e) {
            assertEquals(INVALID_NAME, e.getMessage());
        }
    }

    @Test
    public void testEmptyDeadline() throws InvalidArgumentException, DateTimeException {
        try {
            DeadlineTask deadline = new DeadlineTask("return book", "");
            fail();
        } catch (InvalidArgumentException e) {
            assertEquals(INVALID_DEADLINE, e.getMessage());
        }
    }

    @Test
    public void testEmptyNameAndDeadline() throws InvalidArgumentException, DateTimeException {
        try {
            DeadlineTask deadline = new DeadlineTask("", "");
            fail();
        } catch (InvalidArgumentException e) {
            assertEquals(INVALID_NAME, e.getMessage());
        }
    }

    @Test
    public void testEmptyNameAndInvalidDeadline() throws InvalidArgumentException, DateTimeException {
        try {
            DeadlineTask deadline = new DeadlineTask("", "2020-12-32");
            fail();
        } catch (InvalidArgumentException e) {
            assertEquals(INVALID_NAME, e.getMessage());
        }
    }

    @Test
    public void testMarkAsDone() throws InvalidArgumentException, DateTimeException {
        DeadlineTask deadline = new DeadlineTask("return book", "2020-12-12");
        deadline.markDone(true);
        assertEquals("[D][X] return book (by: Dec 12 2020)", deadline.toString());

    }

    @Test
    public void testMarkAsUndone() throws InvalidArgumentException, DateTimeException {
        DeadlineTask deadline = new DeadlineTask("return book", "2020-12-12");
        deadline.markDone(true);
        deadline.markDone(false);
        assertEquals("[D][ ] return book (by: Dec 12 2020)", deadline.toString());
    }

    @Test
    public void testMarkAsDoneAndFileConversion() throws InvalidArgumentException, DateTimeException {
        DeadlineTask deadline = new DeadlineTask("return book", "2020-12-12");
        deadline.markDone(true);
        assertEquals("D | 1 | return book | 2020-12-12 | ", deadline.toFileString());
    }

    @Test
    public void testMarkAsUndoneAndFileConversion() throws InvalidArgumentException, DateTimeException {
        DeadlineTask deadline = new DeadlineTask("return book", "2020-12-12");
        deadline.markDone(true);
        deadline.markDone(false);
        assertEquals("D | 0 | return book | 2020-12-12 | ", deadline.toFileString());
    }
}
