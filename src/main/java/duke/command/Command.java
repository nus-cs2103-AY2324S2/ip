package duke.command;

import duke.TaskList;

/**
 * Base class for all Commands that are valid.
 */
public abstract class Command {
    /**
     * Execute a command.
     *
     * @param tasks   The list of tasks.
     * @return The reply to the user.
     */
    public abstract String execute(TaskList tasks);

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
