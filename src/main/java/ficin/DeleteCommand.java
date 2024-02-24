package ficin;

import ficin.task.Task;
import ficin.task.TaskList;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 * It is a subclass of the Command class.
 */
public class DeleteCommand extends Command {

    /**
     * Executes the delete command to remove a task from the task list based on user input.
     * This method parses the user input to determine the index of the task to be deleted and then
     * attempts to delete the task from the task list.
     *
     * @param taskList  The task list from which the task will be deleted.
     * @param ui        The user interface for displaying messages.
     * @param userInput The user input specifying the task index to be deleted.
     * @throws DukeException If there is an issue executing the command or if the task index is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
        try {
            int taskIndex = Parser.extractTaskIndex(userInput);
            if (taskIndex <= 0 || taskIndex > taskList.getSize()) {
                throw new DukeException("Invalid task number. Please try again.");
            }
            Task removedTask = taskList.deleteTask(taskIndex);
            ui.showTaskDeleted(removedTask, taskList.getSize());
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number format. Please enter a valid task number.");
        } catch (Exception e) {
            throw new DukeException("An error occurred while trying to delete the task: " + e.getMessage());
        }
    }
}
