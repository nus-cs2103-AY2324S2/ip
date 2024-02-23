package lery.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskTest {
    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", new Task("read book", false).getStatusIcon());
    }
    @Test
    public void testGetDescription() {
        assertEquals("read book", new Task("read book", false).getDescription());
    }
    @Test
    public void testMarkAsDone() {
        Task t = new Task("read book", false);
        t.markAsDone();
        assertEquals(t.getIsDone(), true);
    }
    @Test
    public void testUnmarkAsDone() {
        Task t = new Task("read book", true);
        t.unmarkAsDone();
        assertEquals(t.getIsDone(), false);
    }
    @Test
    public void testGetType() {
        assertEquals("", new Task("read book", false).getType());
    }

    @Test
    public void testGetExtraInfo() {
        assertEquals("", new Task("read book", false).getExtraInfo());
    }
    @Test
    public void testGetExtraInfoShortened() {
        assertEquals("", new Task("read book", false).getExtraInfoShortened());
    }
}
