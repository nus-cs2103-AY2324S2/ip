/**
 * The Command class is an abstract class representing a generic command in the Duke application.
 * Specific commands (e.g., AddCommand, DeleteCommand) will extend this class and implement the 'execute' method.
 */
package duke;

import duke.task.TaskList;

public abstract class Command {

    /**
     * Executes the command based on the provided task list, user interface, and user input.
     *
     * @param taskList   The task list on which the command will operate.
     * @param ui         The user interface for displaying messages.
     * @param userInput  The user input specifying the command and its arguments.
     * @throws DukeException If there is an issue executing the command or if the command encounters an error.
     */
    public abstract void execute(TaskList taskList, Ui ui, String userInput) throws DukeException;
}
