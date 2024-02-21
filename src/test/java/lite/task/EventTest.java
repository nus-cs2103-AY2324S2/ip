package lite.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void saveToFileTest() {
        Task task = new Event("project meeting", "from 2020-08-10 15:30", "to 2020-08-10 17:30");
        assertEquals("E!0!project meeting!from 2020-08-10 15:30!to 2020-08-10 17:30\n", task.saveToFile());
        task.setDone();
        assertEquals("E!1!project meeting!from 2020-08-10 15:30!to 2020-08-10 17:30\n", task.saveToFile());
        task.setUndone();
        assertEquals("E!0!project meeting!from 2020-08-10 15:30!to 2020-08-10 17:30\n", task.saveToFile());
    }

    @Test
    public void toStringTest() {
        Task task = new Event("project meeting", "from 2020-08-10 15:30", "to 2020-08-10 17:30");
        assertEquals("[E][] project meeting(from: AUGUST 10 2020 15:30 to: AUGUST 10 2020 17:30)", task.toString());
        task.setDone();
        assertEquals("[E][X] project meeting(from: AUGUST 10 2020 15:30 to: AUGUST 10 2020 17:30)", task.toString());
    }
}
