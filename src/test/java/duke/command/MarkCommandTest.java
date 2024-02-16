import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import duke.command.MarkCommand;
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
        // Create a task list with tasks
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("Task 1"));
        tasks.addTask(new ToDo("Task 2"));

        // Create a MarkCommand with a valid task index
        MarkCommand markCommand = new MarkCommand(List.of("1"));
        
        // Execute the command
        TaskList updatedTasks = markCommand.execute(tasks);

        // Verify that the first task is marked as done
        assertTrue(updatedTasks.getTask(0).isDone());
    }

    // Write similar tests for other scenarios mentioned above
}
