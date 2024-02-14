package duke.Command;

import duke.TaskList;
import duke.DukeException;

/**
 * The Command class is an abstract class that represents a command in the Duke application.
 * All commands in Duke should extend this class and implement the execute method.
 */
public abstract class Command {

    /**
     * Executes the command based on the given task list and command string.
     *
     * @param taskList The task list to execute the command on.
     * @param command The command string representing the specific command.
     * @return The result of executing the command.
     * @throws DukeException If there is an error executing the command.
     */
    public abstract String execute(TaskList taskList, String command) throws DukeException;
}