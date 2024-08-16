package charlie.commands;

import charlie.exceptions.CharlieException;
import charlie.storage.Storage;
import charlie.storage.TaskList;

public abstract class Command {

    protected boolean isExit;

    /**
     * Abstract execute method that aims to perform actions for a specific command.
     * Needs to be overridden in sub-classes.
     * @param taskList - task list loaded at the start of the program.
     * @param storage - class responsible for adding and loading tasks from and into the file
     * @throws CharlieException
     */
    public abstract String execute(TaskList taskList, Storage storage) throws CharlieException;

    /**
     * getter method for the boolean value, which decides if the program exits or not, used in ExitCommand
     * @return isExit
     */
    public boolean isExit() {
        return isExit;
    }
}
