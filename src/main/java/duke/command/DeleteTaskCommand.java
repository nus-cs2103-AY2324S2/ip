package duke.command;

import duke.task.Task;
import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.io.IOException;

/**
 * Class representing a command that deletes a {@link Task} from the {@link TaskList}.
 */
public class DeleteTaskCommand extends Command {
    /** Index of Task to be deleted from the TaskList. */
    private int indexToBeDeleted;

    /**
     * Constructs a DeletedTaskCommand Object.
     *
     * @param index Index of Task in TaskList to be deleted.
     */
    public DeleteTaskCommand(int index) {
        this.indexToBeDeleted = index;
    }

    /**
     * Runs the DeleteTaskCommand to delete a task from the existing TaskList.
     *
     * @param taskList Existing TaskList to be updated.
     * @param ui Existing UserInterface Object.
     * @param storage Existing Storage to be updated
     * @throws DukeException
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask;
        try {
            deletedTask = taskList.deleteTask(indexToBeDeleted);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("*HONK* Pengu thinks you need a valid task number to delete, "
                    + "consider checking the list command");
        }
        try {
            storage.saveStorage(taskList.getTaskStore());
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
        return ui.showDeletedTask(deletedTask, taskList.listSize());
    }

    /**
     * Returns a boolean value telling us whether this command is an exit command.
     *
     * @return Boolean value indicating whether this command is an exist command.
     */
    public boolean isExit() {
        return false;
    }
}
