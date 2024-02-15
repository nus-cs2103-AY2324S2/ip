package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    private final TaskList testTasks = new TaskList();

    @Test
    public void testGetTask() {
        // get task that doesn't exist
        try {
            testTasks.getTask(0);
            fail();
        } catch (Exception e) {
            assertEquals("Could not find task!", e.getMessage());
        }

        // get task that exists
        testTasks.addTask(new Todo("buy bread", 2));
        String todoDesc = "";
        Integer todoPriority = 0;
        try {
            todoDesc = testTasks.getTask(1).description;
            todoPriority = testTasks.getTask(1).priority;
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }
        assertEquals("buy bread", todoDesc);
        assertEquals(2, todoPriority);
    }

    @Test
    public void testMarkTask() {
        testTasks.addTask(new Deadline("return book", 2, "Mon 4pm"));

        // mark task that doesn't exist
        try {
            testTasks.markTask(5);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }

        // mark task that exists
        try {
            testTasks.markTask(1);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }

        // verify task marked
        try {
            Task t = testTasks.getTask(1);
            assertEquals("return book", t.description);
            assertEquals(true, t.isDone);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }
    }

    @Test
    public void testUnmarkTask() {
        testTasks.addTask(new Deadline("return book", 5, "Mon 4pm"));

        // unmark task that doesn't exist
        try {
            testTasks.unmarkTask(5);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }

        // unmark task that exists
        try {
            testTasks.unmarkTask(1);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }

        // verify task unmarked
        try {
            Task t = testTasks.getTask(1);
            assertEquals("return book", t.description);
            assertEquals(false, t.isDone);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }
    }

    @Test
    public void testDeleteTask() {
        testTasks.addTask(new Deadline("return book", 5, "Mon 4pm"));

        // delete existing task
        try {
            testTasks.deleteTask(1);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }

        // delete task that doesn't exist
        try {
            testTasks.deleteTask(-1);
            fail();
        } catch (Exception e) {
            assertEquals("Could not find task!", e.getMessage());
        }
    }

    @Test
    public void testAddTask() {
        assertEquals(0, testTasks.size());
        Todo t = new Todo("Buy bread", 4);
        testTasks.addTask(t);
        assertEquals(1, testTasks.size());
    }

    @Test
    public void testFindTasks() {
        testTasks.addTask(new Todo("Buy Bread", 5));
        testTasks.addTask(new Todo("Borrow book", 5));
        testTasks.addTask(new Deadline("Return Bread", 3, "tomorrow"));
        testTasks.addTask(new Todo("eat bread", 5));
        testTasks.addTask(new Event("Bread discussion", 10, "Mon 4pm", "5pm"));

        testTasks.findTasks("Bread");
    }

    @Test
    public void testUndo() {
        testTasks.addTask(new Todo("Buy Bread", 5));
        testTasks.undo();

        assertEquals(0, testTasks.size());

        testTasks.addTask(new Deadline("Return Bread", 5, "tomorrow"));
        testTasks.addTask(new Todo("eat bread", 2));
        testTasks.addTask(new Event("Bread discussion", 4, "Mon 4pm", "5pm"));

        // delete existing task
        try {
            testTasks.deleteTask(1);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }
        testTasks.undo();
        assertEquals(3, testTasks.size());
        testTasks.undo();
        assertEquals(2, testTasks.size());
        testTasks.undo();
        assertEquals(3, testTasks.size());

        // mark and undo marking
        try {
            testTasks.markTask(1);
            Task t = testTasks.getTask(1);
            assertEquals("Return Bread", t.description);
            assertEquals(true, t.isDone);
            testTasks.undo();
            t = testTasks.getTask(1);
            assertEquals(false, t.isDone);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }

        // unmark and undo unmarking
        try {
            testTasks.markTask(2);
            testTasks.unmarkTask(2);
            Task t = testTasks.getTask(2);
            assertEquals("eat bread", t.description);
            assertEquals(false, t.isDone);
            testTasks.undo();
            t = testTasks.getTask(2);
            assertEquals(true, t.isDone);
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }
    }
}
