package lery.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    @Test
    public void testGetType() {
        assertEquals("T", new Todo("read book").getType());
    }

    @Test
    public void testGetExtraInfo() {
        assertEquals("", new Todo("read book").getExtraInfo());
    }
    @Test
    public void testGetExtraInfoShortened() {
        assertEquals("", new Todo("read book").getExtraInfoShortened());
    }
}
