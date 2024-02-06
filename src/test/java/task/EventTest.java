package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toString_eventObject_eventString() {
        assertEquals("[E][ ] test (from: Aug 21 2021 18:00 to: Aug 21 2021 19:00)",
                new Event("test", "2021-08-21 18:00", "2021-08-21 19:00").toString());
    }

    @Test
    public void toString_markedEventObject_markedEventString() {
        assertEquals("[E][X] test (from: Aug 21 2021 18:00 to: Aug 21 2021 19:00)",
                new Event("test", "2021-08-21 18:00", "2021-08-21 19:00", true).toString());
    }

    @Test
    public void eventConstructor_differentDateTimeFormat_successfulCreation() {
        Event expected = new Event("test", "2021-08-21 18:00", "2021-08-21 19:00");
        assertEquals(expected.getStart(), new Event("test", "2021/08/21 18:00", "2021/08/21 19:00").getStart());
        assertEquals(expected.getEnd(), new Event("test", "2021.08.21 18:00", "2021.08.21 19:00").getEnd());
        assertEquals(expected.toString(), new Event("test", "2021 08 21 18:00", "2021 08 21 19:00").toString());
    }

    @Test
    public void eventConstructor_timeNotSpecified_setToMidnight() {
        assertEquals(new Event("test", "2021-08-21 00:00", "2021-08-21 00:00").toString(),
                new Event("test", "2021-08-21", "2021-08-21").toString());
    }

    @Test
    public void eventConstructor_wrongDateTimeFormat_exceptionThrown() {
        try {
            new Event("test", "2021-08-21 18:00", "2021-08-21 19:00", true);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid date and time in the format yyyy-MM-dd HH:mm", e.getMessage());
        }
    }

}
