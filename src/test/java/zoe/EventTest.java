package zoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    @Test
    public void testSaveTask() {
        //test that input matches the required save format
        assertEquals("event_project meeting /from 4pm /to 6pm_0",
                new Event("project meeting /from 4pm /to 6pm").saveTask());
    }
}
