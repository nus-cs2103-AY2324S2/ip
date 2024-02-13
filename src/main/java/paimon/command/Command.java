package paimon.command;


import paimon.ChatException;
import paimon.UiHandler;
import paimon.task.TaskList;

/**
 * Represents the abstract base for commands in the application. This class provides a structure
 * for executing commands and determining whether a command should signal the application to exit.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list and UI handler.
     * This method defines how the command modifies the task list or interacts with the user
     * through the UI, depending on the specific command type.
     *
     * @param tasks The task list that the command will operate on.
     * @param ui    The UI handler for interacting with the user.
     * @throws ChatException If an error occurs during command execution.
     */
    public abstract void execute(TaskList tasks, UiHandler ui) throws ChatException;

    /**
     * Determines whether the command is an exit command.
     * This method allows the application to check if executing this command
     * should signal the application to terminate.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}