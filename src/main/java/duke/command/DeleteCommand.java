package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/*
* The DeleteCommand class is a subclass of Command and represents a command to delete a task from the task list.
* It takes in an index as an int.
 */
public class DeleteCommand extends Command {
    protected int index;

    /*
     * Constructs DeleteCommand object with index as an int.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.showTaskDeleted(task, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}