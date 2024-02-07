package adam.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void testDeleteSuccessfully(){
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            for (int i = 0; i < 5; i++) {
                tasks.add(new Todo("desc" + i));
            }
            ArrayList<Task> ls = tasks.getTasks();
            Task t = ls.get(1);
            tasks.delete(2);
            assertEquals(tasks.size(), 4);
            assertFalse(ls.contains(t));
        } catch (Exception e) {
            fail("Taskl not removed from list successfully");
        }
    }

    @Test
    public void testDeleteFail(){
        TaskList tasks = new TaskList(new ArrayList<>());
        for (int i = 0; i < 5; i++) {
            tasks.add(new Todo("desc" + i));
        }
        try {
            tasks.delete(7);
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: You do not have 7 tasks.", e.getMessage());
        }
    }
}
