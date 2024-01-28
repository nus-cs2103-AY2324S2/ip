package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest(){
        Event tsk = new Event("project meeting", "Mon 2pm", "4pm");
        assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", tsk.toString());
    }

    @Test
    public void toSaveTest(){
        Event tsk = new Event("project meeting", "Mon 2pm", "4pm");
        assertEquals("E|0|project meeting|Mon 2pm|4pm", tsk.toSave());
    }
}
