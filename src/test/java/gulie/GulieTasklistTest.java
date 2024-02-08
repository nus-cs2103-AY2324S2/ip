package gulie;

import gulie.task.Task;
import gulie.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GulieTasklistTest {
    @Test
    public void addDeleteTest() {
        GulieTasklist tasklist = new GulieTasklist();
        Task task = new Todo("123");
        assertEquals(tasklist.size(), 0);
        tasklist.add(task);
        assertEquals(tasklist.size(), 1);
        try {
            Task task2 = tasklist.delete(0);
            assertEquals(tasklist.size(), 0);
            assertEquals(task, task2);
        } catch (GulieException ge) {
            fail();
        }
        try {
            tasklist.delete(1);
            fail();
        } catch (GulieException ge) {

        }
    }
}
