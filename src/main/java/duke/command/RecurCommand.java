package duke.command;

import duke.DukeException;
import duke.MyList;
import duke.Ui;

/**
 * Represents a command to make a task recur in the Duke application.
 */
public class RecurCommand extends Command {
    private int index;

    public RecurCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the RecurCommand by making a specified task recur in the task list and adding
     * response to the UI.
     *
     * @param myList The list in which the task will recur.
     * @param ui     The user interface for displaying results.
     */
    public void execute(MyList myList, Ui ui) {
        try {
            String recurTaskMessage = myList.recurTask(index);
            ui.addResponse(recurTaskMessage);
        } catch (DukeException e) {
            ui.addResponse(e.getMessage());
        }
    }
}
