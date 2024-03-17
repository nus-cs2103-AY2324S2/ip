package hal.command;

import hal.task.TaskList;

/**
 * The Command class represents a base class for all commands.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList The TaskList containing the tasks.
     * @return A message indicating the result of the command execution.
     */
    public abstract String execute(TaskList taskList);

    /**
     * Checks if the command is an exit command.
     *
     * @return true if the command is an exit command, otherwise false.
     */
    public boolean isExit() {
        return false;
    }
}
