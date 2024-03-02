package grizzly.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import grizzly.utils.Parser;

public class DeadlineTest {
    @Test
    public void testDeadline() {
        Deadline d = new Deadline(false, "test",
                                  LocalDateTime.parse("12/11/2024, 13:00",
                                                      Parser.INPUT_DT_FORMATTER));
        assertEquals(d.toString(), ("[D][ ] test\n(by: 12 November 2024, 01:00PM)"));
    }

    @Test
    public void testDeadline2() {
        Deadline d = new Deadline(true, "test",
                                  LocalDateTime.parse("12/02/2023, 12:00",
                                                      Parser.INPUT_DT_FORMATTER));
        assertEquals(d.toString(), ("[D][X] test\n(by: 12 February 2023, 12:00PM)"));
    }

    @Test
    public void testDeadlineParse() {
        Hashtable<String, String> testMap = new Hashtable<String, String>();
        testMap.put("by", "12/11/2024, 13:00");
        testMap.put("description", "test");
        try {
            Deadline d = Deadline.deadlineParse(false, testMap);
            assertEquals(d.toString(), ("[D][ ] test\n(by: 12 November 2024, 01:00PM)"));
        } catch (Exception e) {
            fail();
        }
    }
}
