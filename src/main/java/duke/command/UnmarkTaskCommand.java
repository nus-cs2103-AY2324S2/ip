package duke.command;

import duke.task.Task;
import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.io.IOException;

/**
 * Class that represents a Command that unmarks a {@link Task} from the {@link TaskList}.
 */
public class UnmarkTaskCommand extends Command {
    /** Index of the Task in the TaskList to be Unmarked. */
    private int indexToBeUnmarked;

    /**
     * Constructs a UnmarkTaskCommand Object.
     *
     * @param index Index of the Task in the TaskList to be Unmarked.
     */
    public UnmarkTaskCommand(int index) {
        this.indexToBeUnmarked = index;
    }

    /**
     * Runs the UnmarkTaskCommand to unmark an existing Task in the TaskList.
     *
     * @param taskList Existing TaskList to be updated.
     * @param ui Existing UserInterface Object.
     * @param storage Existing Storage to be updated
     * @throws DukeException
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask;
        try {
            unmarkedTask = taskList.unmarkTask(indexToBeUnmarked);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("*HONK* Pengu thinks you need a valid task number to unmark, "
                    + "consider checking the list command");
        }
        try {
            storage.saveStorage(taskList.getTaskStore());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
        return ui.showUnmarkedTask(unmarkedTask);
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
