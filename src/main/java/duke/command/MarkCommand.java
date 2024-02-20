package duke.command;

import duke.DukeException;
import duke.MyList;
import duke.Ui;

/**
 * Represents a command to mark a task as done in the Duke application.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand by marking the specified task as done in the task list and adding
     * response to the UI.
     *
     * @param myList The list in which the task will be marked as done.
     * @param ui     The user interface for displaying results.
     */
    public void execute(MyList myList, Ui ui) {
        try {
            String markTaskMessage = myList.markTask(index);
            ui.addResponse(markTaskMessage);
        } catch (DukeException e) {
            ui.addResponse(e.getMessage());
        }
    }
}
