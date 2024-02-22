package commands;

import destiny.DestinyException;
import destiny.TaskList;

/**
 * The abstract class which all command classes extend from.
 */
public abstract class Command {

    /**
     * The primary command responsible for the functionality of the command.
     *
     * @param tasks The set of tasks saved by Destiny.
     * @return String result of the execution of the command.
     */
    public abstract String execute(TaskList tasks) throws DestinyException;
}
