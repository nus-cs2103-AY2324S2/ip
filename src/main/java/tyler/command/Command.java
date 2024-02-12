package tyler.command;

import tyler.ui.Ui;
import tyler.storage.Storage;
import tyler.task.TaskList;

/**
 * Represents a command.
 */
public abstract class Command {

    /**
     * Determine this command whether is Exit or not.
     *
     * @return If is Exit, true, otherwise false.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Execute the different command respectively. This abstract method will be
     * overridden in command subclasses.
     *
     * @param tasks   The object which stored the list of tasks.
     * @param ui      The User Interface of the program.
     * @param storage The storage that can load or save task.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
