package duke.command;

import duke.MyList;
import duke.Ui;

/**
 * Represents a command to handle invalid user inputs in the Duke application.
 */
public class InvalidCommand extends Command {
    /**
     * Executes the InvalidCommand and informs the user about the invalid input.
     *
     * @param myList The list that is being modified.
     * @param ui     The user interface for displaying the invalid input message.
     */
    public void execute(MyList myList, Ui ui) {
        ui.addResponse("OOPS! That was an invalid input");
    }
}
