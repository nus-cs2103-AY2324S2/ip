/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 * It is a subclass of the Command class.
 */
package duke;

import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command{

    /**
     * Executes the delete command to remove a task from the task list based on user input.
     *
     * @param taskList   The task list from which the task will be deleted.
     * @param ui         The user interface for displaying messages.
     * @param userInput  The user input specifying the task index to be deleted.
     * @throws DukeException If there is an issue executing the command or if the task index is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
        int taskIndex = Parser.extractTaskIndex(userInput);
        Task removedTask = taskList.deleteTask(taskIndex); // Use duke.task.TaskList to delete task
        if (removedTask != null) {
            ui.showTaskDeleted(removedTask, taskList.getSize());
        } else {
            throw new DukeException("Invalid task number. Please try again.");
        }
    }
}
