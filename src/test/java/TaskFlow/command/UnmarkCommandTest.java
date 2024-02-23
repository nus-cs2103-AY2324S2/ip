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

public class UnmarkCommandTest {

    @Test
    public void execute_validIndex_taskUnmarkedSuccessfully() throws DukeException {
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
        DukeException dukeException = assertThrows(DukeException.class, (
        ) -> unmarkCommand.execute(task, archiveTasks, ui, storage, archived));

        // Verify that the exception message is as expected
        assertEquals("Invalid index. Please provide a valid index within"
                        + " the range 1 to 1.", dukeException.getMessage());
    }
}
