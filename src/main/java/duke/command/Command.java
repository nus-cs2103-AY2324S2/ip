package duke.command;

import duke.MyList;
import duke.Ui;

/**
 * Represents an abstract command that can be executed on the Duke application.
 */
public abstract class Command {
    public abstract void execute(MyList myList, Ui ui);

    /**
     * Checks if the command represents an exit command.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
