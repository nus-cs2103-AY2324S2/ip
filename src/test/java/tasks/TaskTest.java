package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TaskTest {

    @Test
    public void test_todo_task_to_string() {
        Task task = new Task("Buy groceries", false);
        assertEquals("[T][ ] Buy groceries", task.toString());
    }

    @Test
    public void test_deadline_task_to_string() {
        Task task = new Task("Submit report", true, "2022-10-31");
        assertEquals("[D][X] Submit report (2022-10-31)", task.toString());
    }

    @Test
    public void test_event_task_to_string() {
        Task task = new Task("Birthday party", false, "2022-12-25", "2022-12-26");
        assertEquals("[E][ ] Birthday party (2022-12-25 to 2022-12-26)", task.toString());
    }

    @Test
    public void test_mark_task_as_done() {
        Task task = new Task("Read book", false);
        assertFalse(task.isDone);
        task.mark();
        assertTrue(task.isDone);
    }

    @Test
    public void test_unmark_task() {
        Task task = new Task("Clean room", true);
        assertTrue(task.isDone);
        task.unmark();
        assertFalse(task.isDone);
    }
}