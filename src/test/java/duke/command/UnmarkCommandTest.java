package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommandTest {

    @Test
    public void execute_validIndex_taskUnmarkedSuccessfully() throws DukeException {
        int validIndex = 1;
        ArrayList<Task> tasks = new ArrayList<>();
        Task taskToMark = new Task("todo blahvblah");
        tasks.add(taskToMark);

        TaskList task = new TaskList(tasks);
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");

        UnmarkCommand unmarkCommand = new UnmarkCommand(validIndex);
        unmarkCommand.execute(task, ui, storage);

        // Verify that the task is unmarked
        assertEquals(" ", taskToMark.getStatusIcon());

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

        UnmarkCommand unmarkCommand = new UnmarkCommand(invalidIndex);
        DukeException dukeException = assertThrows(DukeException.class, (
        ) -> unmarkCommand.execute(task, ui, storage));

        // Verify that the exception message is as expected
        assertEquals("Invalid index. Please provide a valid index within the range 1 to 1.",
                dukeException.getMessage());
    }

    @Test
    public void isExit_alwaysReturnsFalse() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(1);
        assertFalse(unmarkCommand.isExit());
    }
}
