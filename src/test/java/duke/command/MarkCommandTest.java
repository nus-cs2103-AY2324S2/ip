import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import duke.command.MarkCommand;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Test class for the {@code MarkCommand} class.
 */
public class MarkCommandTest {
    /**
     * Tests the behavior of marking a valid task as done.
     */
    @Test
    public void testMarkValidTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));

        MarkCommand markCommand = new MarkCommand(List.of("1"));
        TaskList updatedTasks = markCommand.execute(tasks);

        assertTrue(updatedTasks.getTask(0).isDone());
    }

    /**
     * Tests the behavior of marking a task with an index out of range.
     */
    @Test
    public void testMarkIndexOutOfRange() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));

        MarkCommand markCommand = new MarkCommand(List.of("0"));

        // Execute the command
        TaskList updatedTasks = markCommand.execute(tasks);

        // Verify that the tasks list remains unchanged
        assertEquals(tasks, updatedTasks);
    }

    /**
     * Tests the behavior of marking a task with invalid input.
     */
    @Disabled
    @Test
    public void testMarkInvalidInput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));

        MarkCommand markCommand = new MarkCommand(List.of("abc"));

        // Execute the command
        TaskList updatedTasks = markCommand.execute(tasks);

        // Verify that the tasks list remains unchanged
        assertEquals(tasks, updatedTasks);
    }

    /**
     * Tests the behavior of marking a task in an empty task list.
     */
    @Disabled
    @Test
    public void testMarkTaskInEmptyList() {
        TaskList tasks = new TaskList();

        MarkCommand markCommand = new MarkCommand(List.of("1"));

        // Execute the command
        TaskList updatedTasks = markCommand.execute(tasks);

        // Verify that the tasks list remains unchanged
        assertEquals(tasks, updatedTasks);
    }

    /**
     * Tests the behavior of marking a task when no index is provided.
     */
    @Disabled
    @Test
    public void testMarkNoIndexProvided() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));

        MarkCommand markCommand = new MarkCommand(List.of());

        // Execute the command
        TaskList updatedTasks = markCommand.execute(tasks);

        // Verify that the tasks list remains unchanged
        assertEquals(tasks, updatedTasks);
    }
}
