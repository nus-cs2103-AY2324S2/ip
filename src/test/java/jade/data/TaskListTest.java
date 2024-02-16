package jade.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void size() {
        assertEquals(0, new TaskList().size());
        assertEquals(1, new TaskList(List.of(new Todo("first"))).size());
    }

    @Test
    public void mark_validIndex_success() {
        TaskList testTaskList = new TaskList(List.of(new Todo("first"), new Todo("second")));
        testTaskList.mark(0);
        assertEquals(true, testTaskList.get(0).isDone);
        testTaskList.mark(1);
        assertEquals(true, testTaskList.get(1).isDone);
    }

    @Test
    public void mark_invalidIndex_exceptionThrown() {
        try {
            TaskList testTaskList = new TaskList(List.of(new Todo("first")));
            testTaskList.mark(-1);
            fail();
        } catch (Exception e) {
            assertEquals("Index: -1 Size: 1", e.getMessage());
        }
    }

    @Test
    public void unmark_validIndex_success() {
        TaskList testTaskList = new TaskList(List.of(new Todo("first", true), new Todo("second", true)));
        testTaskList.unmark(0);
        assertEquals(false, testTaskList.get(0).isDone);
        testTaskList.unmark(1);
        assertEquals(false, testTaskList.get(1).isDone);
    }

    @Test
    public void unmark_invalidIndex_exceptionThrown() {
        try {
            TaskList testTaskList = new TaskList(List.of(new Todo("first", true)));
            testTaskList.unmark(2);
            fail();
        } catch (Exception e) {
            assertEquals("Index: 2 Size: 1", e.getMessage());
        }
    }

    @Test
    public void isEmpty() {
        // empty task list
        assertEquals(true, new TaskList().isEmpty());

        // nonempty task list
        assertEquals(false, new TaskList(List.of(new Todo("first"))).isEmpty());
    }

    @Test
    public void listFormatter() {
        assertEquals("T | 1 | first\n", new TaskList(List.of(new Todo("first", true))).listFormatter());
        assertEquals("T | 0 | first\nT | 1 | second\n", new TaskList(List.of(new Todo("first", false),
                new Todo("second", true))).listFormatter());
    }
}
