import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.command.DeleteCommand;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Test class for the {@code DeleteCommand} class.
 */
public class DeleteCommandTest {
    /**
     * Tests the behavior of deleting a valid task.
     */
    @Test
    public void testDeleteValidTask() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));

        DeleteCommand deleteCommand = new DeleteCommand(List.of("1"));
        TaskList updatedTasks = deleteCommand.execute(tasks);

        assertEquals(1, updatedTasks.getNoOfTasks());
    }

    /**
     * Tests the behavior of deleting a task with an index out of range.
     */
    @Test
    public void testDeleteIndexOutOfRange() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));

        DeleteCommand deleteCommand = new DeleteCommand(List.of("0"));

        // Execute the command
        TaskList updatedTasks = deleteCommand.execute(tasks);

        // Verify that the tasks list remains unchanged
        assertEquals(tasks, updatedTasks);
    }

    /**
     * Tests the behavior of deleting a task with invalid input.
     */
    @Test
    public void testDeleteInvalidInput() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));

        DeleteCommand deleteCommand = new DeleteCommand(List.of("abc"));

        // Execute the command
        TaskList updatedTasks = deleteCommand.execute(tasks);

        // Verify that the tasks list remains unchanged
        assertEquals(tasks, updatedTasks);
    }

    /**
     * Tests the behavior of deleting a task in an empty task list.
     */
    @Test
    public void testDeleteTaskInEmptyList() {
        TaskList tasks = new TaskList();

        DeleteCommand deleteCommand = new DeleteCommand(List.of("1"));

        // Execute the command
        TaskList updatedTasks = deleteCommand.execute(tasks);

        // Verify that the tasks list remains unchanged
        assertEquals(tasks, updatedTasks);
    }

    /**
     * Tests the behavior of deleting a task when no index is provided.
     */
    @Test
    public void testDeleteNoIndexProvided() {
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));

        DeleteCommand deleteCommand = new DeleteCommand(List.of());

        // Execute the command
        TaskList updatedTasks = deleteCommand.execute(tasks);

        // Verify that the tasks list remains unchanged
        assertEquals(tasks, updatedTasks);
    }
}
