package duke.command;

import duke.MyList;
import duke.Ui;

/**
 * Represents a command to exit UI.
 */
public class ByeCommand extends Command {
    public void execute(MyList myList, Ui ui) {
        ui.addResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Checks if the command represents an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return true;
    }
}
