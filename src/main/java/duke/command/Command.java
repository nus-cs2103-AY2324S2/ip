package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Base class for all Commands that are valid.
 */
public abstract class Command {
    /**
     * Execute a command.
     *
     * @param tasks   The list of tasks.
     * @param ui      UI interface with the user.
     * @param storage Storage interface for persistence.
     * @return The reply to the user.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Whether this command signals to exit the Chatbot.
     *
     * @return true if should exit, false if not.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Object.equals, required for tests.
     *
     * @param other the other object to check with.
     * @return true if equals false if not.
     */
    public abstract boolean equals(Object other);
}
