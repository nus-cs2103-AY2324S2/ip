package lery.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class TodoTest {
    @Test
    public void testGetType() {
        assertEquals("T", new Todo("read book", false).getType());
    }

    @Test
    public void testGetExtraInfo() {
        assertEquals("", new Todo("read book", false).getExtraInfo());
    }
    @Test
    public void testGetExtraInfoShortened() {
        assertEquals("", new Todo("read book", false).getExtraInfoShortened());
    }
}
