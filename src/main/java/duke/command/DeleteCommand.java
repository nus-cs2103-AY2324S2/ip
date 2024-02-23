package duke.command;

import duke.DukeException;
import duke.MyList;
import duke.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by deleting the specified task from the task list and adding
     * response to the UI.
     *
     * @param myList The list from which the task will be deleted.
     * @param ui     The user interface for displaying results.
     */
    public void execute(MyList myList, Ui ui) {
        try {
            String deletedTaskMessage = myList.delete(index);
            ui.addResponse(deletedTaskMessage);
        } catch (DukeException e) {
            ui.addResponse(e.getMessage());
        }
    }
}
