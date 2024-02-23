package Charlie.commands;

import Charlie.exceptions.CharlieException;
import Charlie.storage.Storage;
import Charlie.storage.TaskList;
import Charlie.ui.Ui;

public abstract class Command {

    protected boolean isExit;

    /**
     * Abstract execute method that aims to perform actions for a specific command. Needs to be overriden in sub-classes.
     * @param taskList - task list loaded at the start of the program.
     * @param ui - class responsible for user interface interactions
     * @param storage - class responsible for adding and loading tasks from and into the file
     * @throws CharlieException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieException;

    public boolean isExit() {
        return isExit;
    }
}