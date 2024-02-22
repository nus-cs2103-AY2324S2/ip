package liv.processor;

import liv.container.Storage;
import liv.exception.LivException;
import liv.container.TaskList;
import liv.ui.Ui;

/**
 * An abstraction of a command in the code.
 */
public abstract class Command {
    /**
     * Executes this command.
     *
     * @param tasks   The list of tasks to operate on.
     * @param ui      The Ui to gives interaction with users.
     * @param storage The storage where the data is stored.
     * @throws LivException
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws LivException;

    /**
     * Checks if this command is a bye command to exit the chatbot.
     * @return True if the command is a ByeCommand, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if this command alters the data stored in the list.
     * @return True if the command alters the task list, false otherwise.
     */
    public boolean hasChangedData() {
        return false;
    }
}
