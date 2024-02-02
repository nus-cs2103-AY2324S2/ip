package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.IllegalParamException;
import duke.task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int id;

    /**
     * Constructs command.
     *
     * @param id Index of task to be deleted.
     */
    public DeleteCommand(int id) {
        this.id = id;
    }

    /**
     * Executes the command, deleting a task, and saving to storage.
     * Also displays messages to user.
     *
     * @param list TaskList object containing current tasks.
     * @param ui To send instructions on how to update the user interface.
     * @param storage To update storage with updated list.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws IllegalParamException {
        list.deleteTask(this.id);
        storage.save(list);
        ui.showMessage("Looks like you have " + list.countTasks() + " things left to do!");

    }
}