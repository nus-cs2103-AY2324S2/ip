package ficin;

import ficin.task.Task;
import ficin.task.TaskList;

/**
 * The MarkCommand class represents a command to mark a task as done in the task list.
 * It is a subclass of the Command class.
 */
public class MarkCommand extends Command {

    /**
     * Executes the mark command to mark a task as done in the task list based on user input.
     *
     * @param taskList   The task list containing the task to be marked as done.
     * @param ui         The user interface for displaying messages.
     * @param userInput  The user input specifying the task index to be marked as done.
     * @throws DukeException If there is an issue executing the command or if the task index is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
        int taskIndex = Parser.extractTaskIndex(userInput);
        Task task = taskList.markTaskAsDone(taskIndex);
        if (task != null) {
            ui.showTaskDone(task);
        } else {
            throw new DukeException("Invalid task number. Please try again.");
        }
    }
}


