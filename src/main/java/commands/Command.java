package commands;

import destiny.DestinyException;
import destiny.TaskList;

/**
 * Abstract class which all command classes extend from.
 */
public abstract class Command {

    /**
     * Responsible for the functionality of the command.
     *
     * @param tasks The set of tasks saved by Destiny.
     * @return String result of the execution of the command.
     */
    public abstract String execute(TaskList tasks) throws DestinyException;
}
