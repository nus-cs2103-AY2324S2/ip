package gulie;

import gulie.task.Task;
import gulie.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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

    @Test
    public void findTest() {
        GulieTasklist tasklist = new GulieTasklist();
        Task task1 = new Todo("235");
        Task task2 = new Todo("165");
        Task task3 = new Todo("396");
        tasklist.add(task1);
        tasklist.add(task2);
        tasklist.add(task3);
        GulieTasklist findlist = tasklist.find("5");
        assertEquals(2, findlist.size());
        assertSame(findlist.get(0), task1);
    }
}
