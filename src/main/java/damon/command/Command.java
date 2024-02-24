package damon.command;

import damon.storage.Storage;
import damon.tasklist.TaskList;
import damon.ui.Ui;

/**
 * Represents Command object which can execute based on user's input.
 */
public abstract class Command {
    protected final String command;

    /**
     * Constructs a new Command object with only String description parameter.
     *
     * @param command User's input command
     */
    public Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }

    public String toString() {
        return "Command";
    }
}
