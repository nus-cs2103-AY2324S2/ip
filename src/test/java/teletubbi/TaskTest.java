package teletubbi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TaskTest {
    @Test
    public void testGetDescription() {
        Task t = new Task("bake cny cookie");
        assertEquals(t.getDescription(), "bake cny cookie");
    }

    @Test
    public void getStatusIcon_taskNotDone_correctOutput() {
        Task t = new Task("bake cny cookie");
        assertEquals(t.getStatusIcon(), " ");
    }

    @Test
    public void getStatusIcon_taskDone_correctOutput() {
        Task t = new Task("bake cny cookie");
        t.markAsDone();
        assertEquals(t.getStatusIcon(), "X");
    }

    @Test
    public void testMarkAsDone() {
        Task t = new Task("bake cny cookie");
        t.markAsDone();
        assertTrue(t.isDone);
    }

    @Test
    public void testUnmark() {
        Task t = new Task("bake cny cookie");
        t.markAsDone();
        t.unmark();
        assertFalse(t.isDone);
    }
}
