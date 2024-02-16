package tyler.command;

import tyler.storage.Storage;
import tyler.task.TaskList;
import tyler.ui.Ui;

/**
 * Represents a command.
 */
public abstract class Command {
    /**
     * Execute the different command respectively. This abstract method will be
     * overridden in command subclasses.
     *
     * @param tasks   The object which stored the list of tasks.
     * @param ui      The User Interface of the program.
     * @param storage The storage that can load or save task.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
