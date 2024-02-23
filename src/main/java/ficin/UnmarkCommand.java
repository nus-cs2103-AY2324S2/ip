package ficin;

import ficin.task.Task;
import ficin.task.TaskList;

/**
 * The UnmarkCommand class represents a command to unmark a task in the task list.
 * It extends the Command class and implements the execute method.
 */
public class UnmarkCommand extends Command {

    /**
     * Executes to unmark a task in the task list.
     *
     * @param taskList  The task list containing the tasks.
     * @param ui        The user interface to interact with the user.
     * @param userInput The user input specifying the task index to be unmarked.
     * @throws DukeException If an error occurs during the execution, such as an invalid task index.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
        int taskIndex = Parser.extractTaskIndex(userInput);
        Task task = taskList.unmarkTaskDone(taskIndex);
        if (task != null) {
            ui.showTaskUndone(task);
        } else {
            throw new DukeException("Invalid task number. Please try again.");
        }
    }
}
