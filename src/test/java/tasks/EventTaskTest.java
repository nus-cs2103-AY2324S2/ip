package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fredricksen.tasks.Deadline;
import fredricksen.tasks.Event;

public class EventTaskTest {
    @Test
    public void eventToString_correctFormat_success() {
        String fromDateTime = "2/12/2019 1800";
        String toDateTime = "3/12/2019 1900";
        Event newTask = new Event("event homework /from " + fromDateTime + " /to " + toDateTime, "E", false);
        assertEquals("[E][] homework (from: Dec 2 2019, 06:00 PM to: Dec 3 2019, 07:00 PM)", newTask.toString());
    }

    @Test
    public void eventToString_incorrectTimeFormatHandled_failure() {
        String fromDateTime = "2/12/2019 100";
        String toDateTime = "3/12/2019 1300";

        Deadline newTask = new Deadline("event homework /from " + fromDateTime + " /to " + toDateTime, "E", false);
        assertEquals("Invalid Date", newTask.toString());
    }

    @Test
    public void eventToString_incorrectDateFormatHandled_failure() {
        String fromDateTime = "2/123/2019 1300";
        String toDateTime = "3/12/2019 1300";
        Deadline newTask = new Deadline("event homework /from " + fromDateTime + " /to " + toDateTime, "E", false);
        assertEquals("Invalid Date", newTask.toString());
    }
}
