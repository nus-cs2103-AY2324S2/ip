package duke.command;

import duke.task.Task;
import duke.utility.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import java.io.IOException;

public class MarkTaskCommand extends Command {
    /** Index of the Task in the TaskList to be Marked. */
    private int indexToBeMarked;

    /**
     * Constructs a MarkTaskCommand Object.
     *
     * @param index Index of the Task in the TaskList to be Marked.
     */
    public MarkTaskCommand(int index) {
        this.indexToBeMarked = index;
    }

    /**
     * Runs the MarkTaskCommand to mark an existing Task in the TaskList.
     *
     * @param taskList Existing TaskList to be updated.
     * @param ui Existing UserInterface Object.
     * @param storage Existing Storage to be updated
     * @throws DukeException
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Task markedTask = taskList.markTask(indexToBeMarked);
            ui.showMarkedTask(markedTask);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("*HONK* Pengu thinks you need a valid task number to delete, "
                    + "consider checking the list command");
        }
        try {
            storage.saveStorage(taskList.getTaskStore());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
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
