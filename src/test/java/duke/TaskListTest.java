package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
        testTasks.addTask(new Todo("buy bread"));
        String todoDesc = "";
        try {
            todoDesc = testTasks.getTask(1).description;
        } catch (DukeException.TaskNotFoundException e) {
            assertEquals("Could not find task!", e.getMessage());
        }
        assertEquals("buy bread", todoDesc);
    }

    @Test
    public void testMarkTask() {
        testTasks.addTask(new Deadline("return book", "Mon 4pm"));

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
        testTasks.addTask(new Deadline("return book", "Mon 4pm"));

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
        testTasks.addTask(new Deadline("return book", "Mon 4pm"));

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
        Todo t = new Todo("Buy bread");
        testTasks.addTask(t);
        assertEquals(1, testTasks.size());
    }
}