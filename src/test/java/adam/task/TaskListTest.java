package adam.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void testDeleteTaskRemoved(){
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
            fail("Task not removed from list successfully");
        }
    }

    @Test
    public void testDeleteCorrectTaskReturned(){
        try {
            TaskList tasks = new TaskList(new ArrayList<>());
            for (int i = 0; i < 5; i++) {
                tasks.add(new Todo("desc" + i));
            }
            ArrayList<Task> ls = tasks.getTasks();
            Task t = ls.get(1);
            String deleted = tasks.delete(2);
            assertEquals(deleted, t.toString());;
        } catch (Exception e) {
            fail("Task returned by delete is not the one deleted");
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
        try {
            tasks.delete(-3);
            fail();
        } catch (Exception e) {
            assertEquals("ERROR: You do not have -3 tasks.", e.getMessage());
        }
    }
}
