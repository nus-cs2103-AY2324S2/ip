package johnny.tasks;

import johnny.exceptions.JohnnyException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void get_indexWithinBounds_success() throws JohnnyException {
        Task expected = new Task("dummy");
        List<Task> list = new ArrayList<>();
        list.add(expected);
        TaskList tasks = new TaskList(list);
        assertEquals(expected, tasks.get(0));
    }

    @Test
    public void get_indexOutOfBounds_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.get(-1);
            fail();
        } catch (JohnnyException e) {
            assertEquals("This task does not exist bro.", e.getMessage());
        }
    }

    @Test
    public void mark_indexWithinBounds_success() throws JohnnyException {
        Task expected = new Task("dummy");
        expected.mark();
        List<Task> list = new ArrayList<>();
        list.add(expected);
        TaskList tasks = new TaskList(list);
        assertEquals(expected, tasks.mark(0));
    }

    @Test
    public void unmark_indexWithinBounds_success() throws JohnnyException {
        Task expected = new Task("dummy");
        expected.unmark();
        List<Task> list = new ArrayList<>();
        list.add(expected);
        TaskList tasks = new TaskList(list);
        assertEquals(expected, tasks.unmark(0));
    }

    @Test
    public void delete_indexWithinBounds_success() throws JohnnyException {
        Task expected = new Task("dummy");
        List<Task> list = new ArrayList<>();
        list.add(expected);
        TaskList tasks = new TaskList(list);
        assertEquals(expected, tasks.delete(0));
        assertEquals(0, list.size());
    }

    @Test
    public void delete_indexOutOfBounds_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.delete(-1);
            fail();
        } catch (JohnnyException e) {
            assertEquals("This task does not exist bro.", e.getMessage());
        }
    }

    @Test
    public void size_filledList_success() {
        List<Task> list = new ArrayList<>();
        Task dummy1 = new Task("dummy");
        Task dummy2 = new Task("dummy");
        list.add(dummy1);
        list.add(dummy2);
        TaskList tasks = new TaskList(list);
        assertEquals(2, tasks.size());
    }

    @Test
    public void size_emptyList_success() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
    }

}
