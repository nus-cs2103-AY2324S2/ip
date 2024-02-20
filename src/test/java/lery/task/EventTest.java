package lery.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testGetType() {
        assertEquals("E", new Event("project meeting /from Mon 2pm /to 4pm", false).getType());

    }

    @Test
    public void testGetExtraInfo() {
        assertEquals(" (from: Mon 2pm to: 4pm)", new Event("project meeting /from Mon 2pm /to 4pm",
                false).getExtraInfo());

    }
    @Test
    public void testGetExtraInfoShortened() {
        assertEquals("Mon 2pm-4pm", new Event("project meeting /from Mon 2pm /to 4pm", false).getExtraInfoShortened());

    }
}
