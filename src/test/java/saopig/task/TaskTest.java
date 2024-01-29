package saopig.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToString() {
        assertEquals("[ ] read book", new Task("read book").toString());
    }

    @Test
    public void testGetDescription() {
        assertEquals("read book", new Task("read book").getDescription());
    }

    @Test
    public void testGetIsDoneState() {
        assertEquals(false, new Task("read book").getIsDoneState());
    }

    @Test
    public void testMarkAsDone() {
        Task task = new Task("read book");
        task.markAsDone();
        assertEquals(true, task.getIsDoneState());
    }

    @Test
    public void testUnmarkAsDone() {
        Task task = new Task("read book");
        task.markAsDone();
        task.unmarkAsDone();
        assertEquals(false, task.getIsDoneState());
    }

    @Test
    public void testGetStatusIcon() {
        Task task = new Task("read book");
        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void testToStringWithDoneTask() {
        Task task = new Task("read book");
        task.markAsDone();
        assertEquals("[X] read book", task.toString());
    }

    @Test
    public void testToStringWithUndoneTask() {
        Task task = new Task("read book");
        assertEquals("[ ] read book", task.toString());
    }

}
