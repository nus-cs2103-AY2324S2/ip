package duke.command;

import duke.MyList;
import duke.Ui;

/**
 * Represents a command to display the list of tasks in the Duke application.
 */
public class ListCommand extends Command {
    /**
     * Executes the ListCommand by retrieving the list of tasks and adding
     * response to the UI.
     *
     * @param myList The task list to retrieve tasks from.
     * @param ui     The user interface for displaying the list of tasks.
     */
    public void execute(MyList myList, Ui ui) {
        String showListMessage = myList.getTasks();
        ui.addResponse(showListMessage);
    }
}
