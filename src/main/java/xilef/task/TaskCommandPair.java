package xilef.task;

import xilef.command.Command;

/**
 * A {@code TaskCommandPair} represents a pair with a command and the task it is directed on.
 */
public class TaskCommandPair {
    /**
     * The task of the {@code TaskCommandPair}.
     */
    private final Task task;

    /**
     * The command of the {@code TaskCommandPair}.
     */
    private final Command command;

    /**
     * Creates a new {@code TaskCommandPair} with the given task and command.
     *
     * @param task The task of the {@code TaskCommandPair}.
     * @param command The command of the {@code TaskCommandPair}.
     */
    public TaskCommandPair(Task task, Command command) {
        this.task = task;
        this.command = command;
    }

    /**
     * Returns the task of the {@code TaskCommandPair}.
     *
     * @return The task of the {@code TaskCommandPair}
     */
    public Task getTask() {
        return task;
    }

    /**
     * Returns the command of the {@code TaskCommandPair}.
     *
     * @return The command of the {@code TaskCommandPair}
     */
    public Command getCommand() {
        return command;
    }
}
