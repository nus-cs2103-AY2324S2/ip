package grizzly.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import grizzly.utils.Parser;

public class EventTest {
    @Test
    public void testEvent() {
        Event e = new Event(false,
                            "test",
                            LocalDateTime.parse("12/11/2024, 13:00", Parser.INPUT_DT_FORMATTER),
                            LocalDateTime.parse("12/11/2024, 14:00", Parser.INPUT_DT_FORMATTER));
        assertEquals(e.toString(), ("[E][ ] test\n"
                                        + "(from: 12 November 2024, 01:00PM)\n"
                                        + "(to: 12 November 2024, 02:00PM)"));
    }

    @Test
    public void testEvent2() {
        Event e = new Event(false,
                            "test",
                            LocalDateTime.parse("12/02/2023, 12:00", Parser.INPUT_DT_FORMATTER),
                            LocalDateTime.parse("12/02/2023, 13:00", Parser.INPUT_DT_FORMATTER));
        assertEquals(e.toString(), ("[E][ ] test\n"
                                       + "(from: 12 February 2023, 12:00PM)\n"
                                       + "(to: 12 February 2023, 01:00PM)"));
    }

    @Test
    public void testEvent3() {
        Event e = new Event(true,
                            "test",
                            LocalDateTime.parse("12/02/2023, 12:00", Parser.INPUT_DT_FORMATTER),
                            LocalDateTime.parse("12/02/2023, 13:00", Parser.INPUT_DT_FORMATTER));
        assertEquals(e.toString(), ("[E][X] test\n"
                                       + "(from: 12 February 2023, 12:00PM)\n"
                                       + "(to: 12 February 2023, 01:00PM)"));
    }

    @Test
    public void testEventParse() {
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("from", "12/11/2024, 13:00");
        testMap.put("to", "12/11/2024, 14:00");
        testMap.put("description", "test");
        try {
            Event e = Event.eventParse(false, testMap);
            assertEquals(e.toString(), ("[E][ ] test\n"
                                           + "(from: 12 November 2024, 01:00PM)\n"
                                           + "(to: 12 November 2024, 02:00PM)"));
        } catch (Exception e) {
            fail();
        }
    }
}
