package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTaskTest {

    @Test
    public void testStringConversion() {
        EventTask event = null;
        try {
            event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
            assertEquals("[E][ ] project meeting (from: Aug 25 2021 to: Aug 26 2021)", event.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testFileConversion() {
        EventTask event = null;
        try {
            event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
            assertEquals("E | 0 | project meeting | 2021-08-25 | 2021-08-26", event.toFileString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testInvalidDate() {
        try {
            EventTask event = new EventTask("project meeting", "2021-08-32", "2021-08-26");
            fail();
        } catch (DukeException e) {
            assertEquals("can you follow the format yyyy-mm-dd pls", e.getMessage());
        }
    }

    @Test
    public void testEmptyName() {
        try {
            EventTask event = new EventTask("", "2021-08-25", "2021-08-26");
            fail();
        } catch (DukeException e) {
            assertEquals("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21", e.getMessage());
        }
    }

    @Test
    public void testMarkAsDone() {
        EventTask event = null;
        try {
            event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
            event.markDone(true);
            assertEquals("[E][X] project meeting (from: Aug 25 2021 to: Aug 26 2021)", event.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMarkAsUndone() {
        EventTask event = null;
        try {
            event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
            event.markDone(true);
            event.markDone(false);
            assertEquals("[E][ ] project meeting (from: Aug 25 2021 to: Aug 26 2021)", event.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMarkAsDoneAndFileConversion() {
        EventTask event = null;
        try {
            event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
            event.markDone(true);
            assertEquals("E | 1 | project meeting | 2021-08-25 | 2021-08-26", event.toFileString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMarkAsUndoneAndFileConversion() {
        EventTask event = null;
        try {
            event = new EventTask("project meeting", "2021-08-25", "2021-08-26");
            event.markDone(true);
            event.markDone(false);
            assertEquals("E | 0 | project meeting | 2021-08-25 | 2021-08-26", event.toFileString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
