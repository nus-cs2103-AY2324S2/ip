package lemona.command;

import lemona.oop.TaskList;

/**
 * An abstract Command class to handle different commands.
 */
public abstract class Command {

    /**
     * executes the command object's command.
     *
     * @param tasks the task to perform the command on
     * @return The string message to be printed
     */
    public abstract String execute(TaskList tasks);
}
