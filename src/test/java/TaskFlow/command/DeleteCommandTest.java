package TaskFlow.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import TaskFlow.exception.DukeException;
import TaskFlow.storage.Storage;
import TaskFlow.task.Task;
import TaskFlow.task.TaskList;
import TaskFlow.ui.Ui;

public class DeleteCommandTest {

    @Test
    public void execute_validIndex_success() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.add(new Task("todo smtg"));
        tasks.add(new Task("event meeting /from Monday 6pm /to 7pm"));
        tasks.add(new Task("deadline ip /by 2024-02-05 06:00pm"));

        TaskList archiveTasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taskie.txt");
        Storage archived = new Storage("./data/archive.txt");

        // Deleting task at index 2
        Command deleteCommand = new DeleteCommand(2);
        deleteCommand.execute(tasks, archiveTasks, ui, storage, archived);

        // After deletion, only 2 tasks should remain
        assertEquals(2, tasks.getTaskSize());
        assertEquals("todo smtg", tasks.getTaskDescription(0));
        assertEquals("deadline ip /by 2024-02-05 06:00pm",
                tasks.getTaskDescription(1));

    }

    @Test
    public void execute_invalidIndex_exceptionThrown() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.add(new Task("todo smtg"));
        tasks.add(new Task("Task 2"));
        tasks.add(new Task("deadline ip /by 2024-02-05 06:00pm"));

        TaskList archiveTasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./data/taskie.txt");
        Storage archived = new Storage("./data/archive.txt");

        // Deleting task at invalid index 0
        Command deleteCommand = new DeleteCommand(0);

        // The execution of the command should throw a DukeException
        assertThrows(DukeException.class, (
            )-> deleteCommand.execute(tasks, archiveTasks, ui,
                storage, archived));
    }
}
