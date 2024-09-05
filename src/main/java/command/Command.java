package command;

import storage.Storage;
import tasklist.TaskList;

/**
 * An abstract class representing a command in the task management system.
 */
public abstract class Command {

    /**
     * Enum representing various types of commands.
     */
    public enum Types {
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        QUERY,
        FIND;
    }

    /**
     * Executes the command, modifying the task list and storage as needed.
     *
     * @param tasks   The {@code TaskList} to be modified.
     * @param storage The {@code Storage} to be modified.
     */
    public abstract String execute(TaskList tasks, Storage storage);

    /**
     * Gets test data associated with the command.
     *
     * @return A string containing test data for the command.
     */
    public abstract String getTestData();
}
