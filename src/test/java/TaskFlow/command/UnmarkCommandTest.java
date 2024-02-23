package TaskFlow.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import TaskFlow.exception.TaskFlowException;
import TaskFlow.storage.Storage;
import TaskFlow.task.Task;
import TaskFlow.task.TaskList;
import TaskFlow.ui.Ui;

/**
 * A test class for testing the functionality of the UnmarkCommand class.
 */
public class UnmarkCommandTest {

    /**
     * To test that executing UnmarkCommand with a valid index unmarks the task as
     * completed successfully.
     *
     * @throws TaskFlowException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void execute_validIndex_taskUnmarkedSuccessfully() throws TaskFlowException {
        int validIndex = 1;
        ArrayList<Task> tasks = new ArrayList<>();
        Task taskToMark = new Task("todo blahvblah");
        tasks.add(taskToMark);

        TaskList task = new TaskList(tasks);
        TaskList archiveTasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taskie.txt");
        Storage archived = new Storage("./data/archive.txt");

        UnmarkCommand unmarkCommand = new UnmarkCommand(validIndex);
        unmarkCommand.execute(task, archiveTasks, ui, storage, archived);
        assertEquals(" ", taskToMark.getStatusIcon());
        assertEquals(tasks, task.getTasks());
    }

    /**
     * To test that executing UnmarkCommand with an invalid index throws
     * the expected TaskFlowException.
     */
    @Test
    public void execute_invalidIndex_exceptionThrown() {
        int invalidIndex = 0;
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("todo blahvblah"));

        TaskList task = new TaskList(tasks);
        TaskList archiveTasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taskie.txt");
        Storage archived = new Storage("./data/archive.txt");

        UnmarkCommand unmarkCommand = new UnmarkCommand(invalidIndex);
        TaskFlowException taskFlowException = assertThrows(TaskFlowException.class, (
        ) -> unmarkCommand.execute(task, archiveTasks, ui, storage, archived));

        // Verify that the exception message is as expected
        assertEquals("Invalid index. Please provide a valid index within"
                        + " the range 1 to 1.", taskFlowException.getMessage());
    }
}
