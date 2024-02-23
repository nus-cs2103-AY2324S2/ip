package junjie.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.DateTimeException;

import org.junit.jupiter.api.Test;

import junjie.exceptions.InvalidArgumentException;

/**
 * JUnit test class for the EventTask class.
 */
public class EventTaskTest {
    private static final String INVALID_DATE_FORMAT = "eh the date/time format is wrong la, must be yyyy-mm-dd";
    private static final String INVALID_NAME = "oi the task needs a name la \uD83D\uDE21\uD83D\uDE21";
    private static final String INVALID_FROM = "eh the event needs a start date/time";
    private static final String INVALID_TO = "eh the event needs a end date/time";

    @Test
    public void testStringConversion() throws DateTimeException, InvalidArgumentException {
        EventTask event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
        assertEquals("[E][ ] project meeting (from: Aug 25 2021 to: Aug 26 2021)", event.toString());
    }

    @Test
    public void testFileConversion() throws DateTimeException, InvalidArgumentException {
        EventTask event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
        assertEquals("E | 0 | project meeting | 2021-08-25 | 2021-08-26 | ", event.toFileString());
    }

    @Test
    public void testInvalidDate() throws DateTimeException, InvalidArgumentException {
        try {
            EventTask event = new EventTask("project meeting", "2021-08-32", "2021-08-26");
            fail();
        } catch (DateTimeException e) {
            assertEquals(INVALID_DATE_FORMAT, e.getMessage());
        }
    }

    @Test
    public void testEmptyName() throws DateTimeException, InvalidArgumentException {
        try {
            EventTask event = new EventTask("", "2021-08-25", "2021-08-26");
            fail();
        } catch (InvalidArgumentException e) {
            assertEquals(INVALID_NAME, e.getMessage());
        }
    }

    @Test
    public void testEmptyFrom() throws DateTimeException, InvalidArgumentException {
        try {
            EventTask event = new EventTask("project meeting", "", "2021-08-26");
            fail();
        } catch (InvalidArgumentException e) {
            assertEquals(INVALID_FROM, e.getMessage());
        }
    }

    @Test
    public void testEmptyTo() throws DateTimeException, InvalidArgumentException {
        try {
            EventTask event = new EventTask("project meeting", "2021-08-25", "");
            fail();
        } catch (InvalidArgumentException e) {
            assertEquals(INVALID_TO, e.getMessage());
        }
    }

    @Test
    public void testMarkAsDone() throws DateTimeException, InvalidArgumentException {
        EventTask event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
        event.markDone(true);
        assertEquals("[E][X] project meeting (from: Aug 25 2021 to: Aug 26 2021)", event.toString());
    }

    @Test
    public void testMarkAsUndone() throws DateTimeException, InvalidArgumentException {
        EventTask event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
        event.markDone(true);
        event.markDone(false);
        assertEquals("[E][ ] project meeting (from: Aug 25 2021 to: Aug 26 2021)", event.toString());
    }

    @Test
    public void testMarkAsDoneAndFileConversion() throws DateTimeException, InvalidArgumentException {
        EventTask event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
        event.markDone(true);
        assertEquals("E | 1 | project meeting | 2021-08-25 | 2021-08-26 | ", event.toFileString());
    }

    @Test
    public void testMarkAsUndoneAndFileConversion() throws DateTimeException, InvalidArgumentException {
        EventTask event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
        event.markDone(true);
        event.markDone(false);
        assertEquals("E | 0 | project meeting | 2021-08-25 | 2021-08-26 | ", event.toFileString());
    }

    @Test
    public void testSingleTag() throws InvalidArgumentException, DateTimeException {
        EventTask event = new EventTask("project meeting", "2021-08-25", "2021-08-26", new String[] {"important"});
        assertEquals("[E][ ] project meeting (Tags: important) (from: Aug 25 2021 to: Aug 26 2021)", event.toString());
    }

    @Test
    public void testMultipleTags() throws InvalidArgumentException, DateTimeException {
        EventTask event = new EventTask("project meeting", "2021-08-25", "2021-08-26", new String[] {"important", "urgent"});
        assertEquals("[E][ ] project meeting (Tags: important, urgent) (from: Aug 25 2021 to: Aug 26 2021)", event.toString());
    }

    @Test
    public void testSingleTagAndFileConversion() throws InvalidArgumentException, DateTimeException {
        EventTask event = new EventTask("project meeting", "2021-08-25", "2021-08-26", new String[] {"important"});
        assertEquals("E | 0 | project meeting | 2021-08-25 | 2021-08-26 | important", event.toFileString());
    }

    @Test
    public void testMultipleTagsAndFileConversion() throws InvalidArgumentException, DateTimeException {
        EventTask event = new EventTask("project meeting", "2021-08-25", "2021-08-26", new String[] {"important", "urgent"});
        assertEquals("E | 0 | project meeting | 2021-08-25 | 2021-08-26 | important urgent", event.toFileString());
    }
}
