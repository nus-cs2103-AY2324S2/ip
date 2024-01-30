package Lery.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testGetType(){
        assertEquals("E", new Event("project meeting /from Mon 2pm /to 4pm").getType());

    }

    @Test
    public void testGetExtraInfo(){
        assertEquals(" (from: Mon 2pm to: 4pm)", new Event("project meeting /from Mon 2pm /to 4pm").getExtraInfo());

    }
    @Test
    public void testGetExtraInfoShortened(){
        assertEquals("Mon 2pm-4pm", new Event("project meeting /from Mon 2pm /to 4pm").getExtraInfoShortened());

    }
}
