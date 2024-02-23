package TaskFlow.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import TaskFlow.exception.TaskFlowException;
import org.junit.jupiter.api.Test;

import TaskFlow.storage.Storage;
import TaskFlow.task.Task;
import TaskFlow.task.TaskList;
import TaskFlow.ui.Ui;

import java.util.ArrayList;

/**
 * A test class for testing the functionality of the DeleteCommand class.
 */
public class DeleteCommandTest {

    /**
     * To test that executing DeleteCommand with a valid index removes the task correctly.
     *
     * @throws TaskFlowException If there is an unexpected Duke related exception during the test.
     */
    @Test
    public void execute_validIndex_success() throws TaskFlowException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task t1 = new Task("todo smtg");
        Task t2 = new Task("event meeting /from Monday 6pm /to 7pm");
        Task t3 = new Task("deadline ip /by 2024-02-05 06:00pm");
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        TaskList task = new TaskList(tasks);
        TaskList archiveTasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taskie.txt");
        Storage archived = new Storage("./data/archive.txt");

        // Deleting task at index 2
        Command deleteCommand = new DeleteCommand(2);
        deleteCommand.execute(task, archiveTasks, ui, storage, archived);

        // After deletion, only 2 tasks should remain
        assertEquals(2, task.getTaskSize());
        assertEquals("todo smtg", task.getTaskDescription(0));
        assertEquals("deadline ip /by 2024-02-05 06:00pm",
                task.getTaskDescription(1));

    }

    /**
     * To test that executing DeleteCommand with an invalid index throws the expected exception.
     *
     * @throws TaskFlowException If the expected TaskFlowException is not thrown during the test.
     */
    @Test
    public void execute_invalidIndex_exceptionThrown() throws TaskFlowException {
        ArrayList<Task> tasks = new ArrayList<>();
        Task t1 = new Task("todo smtg");
        Task t2 = new Task("event meeting /from Monday 6pm /to 7pm");
        Task t3 = new Task("deadline ip /by 2024-02-05 06:00pm");
        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        TaskList task = new TaskList(tasks);
        TaskList archiveTasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taskie.txt");
        Storage archived = new Storage("./data/archive.txt");

        // Deleting task at invalid index 0
        Command deleteCommand = new DeleteCommand(0);

        // The execution of the command should throw a TaskFlowException
        assertThrows(TaskFlowException.class, (
            )-> deleteCommand.execute(task, archiveTasks, ui,
                storage, archived));
    }
}
