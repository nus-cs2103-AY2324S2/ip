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
     * @return missa.TaskList after executing missa.command.
     */
    public abstract TaskList execute();

    /**
     * Returns a message to indicate the task is completed.
     *
     * @param ui Ui used to produce messages.
     * @return A string of messages.
     */
    public abstract String getReply(Ui ui);

    /**
     * Returns true if this is a Bye missa.command.Command.
     *
     * @return True if this is a Bye missa.command.Command.
     */
    public abstract boolean isBye();
}
