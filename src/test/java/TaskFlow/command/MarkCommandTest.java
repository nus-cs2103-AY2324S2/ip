package TaskFlow.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import TaskFlow.exception.DukeException;
import TaskFlow.storage.Storage;
import TaskFlow.task.Task;
import TaskFlow.task.TaskList;
import TaskFlow.ui.Ui;

/**
 * A test class for testing the functionality of the MarkCommand class.
 */
public class MarkCommandTest {

    /**
     * To test that executing MarkCommand with a valid index marks the task as
     * completed successfully.
     *
     * @throws DukeException If there is an unexpected Duke exception during the test.
     */
    @Test
    public void execute_validIndex_taskMarkedSuccessfully() throws DukeException {
        int validIndex = 1;
        ArrayList<Task> tasks = new ArrayList<>();
        Task taskToMark = new Task("todo blahvblah");
        tasks.add(taskToMark);

        TaskList task = new TaskList(tasks);
        TaskList archiveTasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        Storage archived = new Storage("./data/archive.txt");

        MarkCommand markCommand = new MarkCommand(validIndex);
        markCommand.execute(task, archiveTasks, ui, storage, archived);
        assertEquals("X", taskToMark.getStatusIcon());
        assertEquals(tasks, task.getTasks());
    }

    /**
     * To test that executing MarkCommand with an invalid index throws
     * the expected DukeException.
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

        // Create MarkCommand and execute, assert DukeException is thrown
        MarkCommand markCommand = new MarkCommand(invalidIndex);
        DukeException dukeException = assertThrows(DukeException.class, (
                ) -> markCommand.execute(task, archiveTasks, ui, storage, archived));

        // Verify that the exception message is as expected
        assertEquals("Invalid index. Please provide a valid index within the range 1 to 1.",
                dukeException.getMessage());
    }
}
