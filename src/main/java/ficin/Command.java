package ficin;

import ficin.task.TaskList;

/**
 * The Command class is an abstract class representing a generic command in the Duke application.
 * Specific commands (e.g., AddCommand, DeleteCommand) will extend this class and implement the 'execute' method
 * to perform actions specific to each command based on the user input, modifying or querying the task list as needed.
 */
public abstract class Command {

    /**
     * Executes the command based on the provided task list, user interface, and user input.
     * This method must be implemented by all subclasses to define the specific behavior of each command.
     *
     * @param taskList   The task list on which the command will operate,
     *                   allowing commands to modify or query the task list.
     * @param ui         The user interface for displaying messages to the user, enabling interaction and feedback.
     * @param userInput  The user input specifying the command and its arguments,
     *                   which determines the actions to be performed.
     * @throws DukeException If there is an issue executing the command or if the command encounters an error,
     *                   providing error handling.
     */
    public abstract void execute(TaskList taskList, Ui ui, String userInput) throws DukeException;
}
