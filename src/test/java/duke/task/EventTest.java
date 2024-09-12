package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void event_toStringTest() {
        Event tsk = new Event("project meeting", "Mon 2pm", "4pm");
        assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", tsk.toString());
    }

    @Test
    public void event_toSaveTest() {
        Event tsk = new Event("project meeting", "Mon 2pm", "4pm");
        assertEquals("E|0|project meeting|Mon 2pm|4pm", tsk.toSave());
    }
}
