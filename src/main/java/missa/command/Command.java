package missa.command;

import missa.TaskList;
import missa.Ui;

/**
 * Represents different commands from users.
 */
public abstract class Command {
    /**
     * Executes commands.
     *
     * @param ui missa.Ui used to reply users.
     * @return missa.TaskList after executing missa.command.
     */
    public abstract TaskList execute(Ui ui);

    /**
     * Returns true if this is a Bye missa.command.Command.
     *
     * @return True if this is a Bye missa.command.Command.
     */
    public abstract boolean isBye();
}
