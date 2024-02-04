package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MarkCommandTest {

    @Test
    public void execute_validIndex_taskMarkedSuccessfully() throws DukeException {
        int validIndex = 1;
        ArrayList<Task> tasks = new ArrayList<>();
        Task taskToMark = new Task("todo blahvblah");
        tasks.add(taskToMark);

        TaskList task = new TaskList(tasks);
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");

        MarkCommand markCommand = new MarkCommand(validIndex);
        markCommand.execute(task, ui, storage);

        // Verify that the task is marked
        assertEquals("X", taskToMark.getStatusIcon());

        // Verify that save is called with the updated task list
        assertEquals(tasks, task.getTasks());
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        int invalidIndex = 0;
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("todo blahvblah"));

        TaskList task = new TaskList(tasks);
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");

        // Create MarkCommand and execute, assert DukeException is thrown
        MarkCommand markCommand = new MarkCommand(invalidIndex);
        DukeException dukeException = assertThrows(DukeException.class,
                () -> markCommand.execute(task, ui, storage));

        // Verify that the exception message is as expected
        assertEquals("Invalid index. Please provide a valid index within the range 1 to 1.",
                dukeException.getMessage());
    }

    @Test
    public void isExit_alwaysReturnsFalse() {
        MarkCommand markCommand = new MarkCommand(1);
        assertFalse(markCommand.isExit());
    }
}