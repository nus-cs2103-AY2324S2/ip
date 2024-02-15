package shon.command;

import java.time.format.DateTimeParseException;

import shon.exception.ParameterException;

import shon.TaskList;
import shon.Ui;

/**
 * Represents a command that can be executed to carry out an action.
 */
public abstract class Command {
    /**
     * Executes the specific command.
     *
     * @param tasks The <code>TaskList</code> to add the <code>Deadline</code> task to.
     * @throws ParameterException If the index given in <code>mark</code>, <code>unmark</code> or <code>delete</code>
     * command is not valid.
     * @throws DateTimeParseException If the datetime given in an <code>AddTaskCommand</code> does not adhere to the
     * expected parse format.
     */
    public abstract String execute(TaskList tasks)
            throws ParameterException, DateTimeParseException;

    /**
     * Returns false for all commands that are not <code>ExitCommand</code>.
     *
     * @return true if the command is <code>ExitCommand</code>, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
