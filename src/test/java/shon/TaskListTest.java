package shon;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import shon.exception.ParameterException;
import shon.task.TaskList;

public class TaskListTest {

    @Test
    public void getList_emptyList() {
        assertArrayEquals(new String[]{"Your list of tasks is currently empty."}, new TaskList().getTasks());
    }

    @Test
    public void mark_emptyList_exceptionThrown() {
        try {
            assertEquals(new String[0], new TaskList().mark(1));
            fail();
        } catch (ParameterException e) {
            assertEquals("Please select a valid task number from the list.", e.getMessage());
        }
    }

    @Test
    public void mark_normalList() {
        try {
            TaskList list = new TaskList();
            list.addTodo("Read book");
            assertArrayEquals(new String[]{"Nice! I've marked this task as done:", "  [T][X] Read book"}, list.mark(1));
        } catch (ParameterException e) {
            fail();
        }
    }

    @Test
    public void mark_invalidIndex_exceptionThrown() {
        try {
            TaskList list = new TaskList();
            assertArrayEquals(new String[0], list.mark(-1));
        } catch (ParameterException e) {
            assertEquals("Please select a valid task number from the list.", e.getMessage());
        }
    }

    @Test
    public void unmark_emptyList_exceptionThrown() {
        try {
            assertEquals(new String[0], new TaskList().unmark(2));
            fail();
        } catch (ParameterException e) {
            assertEquals("Please select a valid task number from the list.", e.getMessage());
        }
    }

    @Test
    public void unmark_normalList() {
        try {
            TaskList list = new TaskList();
            list.addTodo("Read book");
            assertArrayEquals(new String[]{"OK, I've marked this task as not done yet:", "  [T][ ] Read book"}, list.unmark(1));
        } catch (ParameterException e) {
            fail();
        }
    }

    @Test
    public void unmark_invalidIndex_exceptionThrown() {
        try {
            TaskList list = new TaskList();
            assertArrayEquals(new String[0], list.unmark(-2));
        } catch (ParameterException e) {
            assertEquals("Please select a valid task number from the list.", e.getMessage());
        }
    }

    @Test
    public void addTodo_defaultFalse() {
        TaskList list = new TaskList();
        assertArrayEquals(new String[]{"Got it. I've added this task:", "  [T][ ] Read book",
                "Now you have 1 task in the list."}, list.addTodo("Read book"));
    }
}