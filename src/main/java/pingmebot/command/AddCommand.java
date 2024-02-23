package pingmebot.command;

import pingmebot.PingMeException;
import pingmebot.Storage;
import pingmebot.TaskList;
import pingmebot.UI;
import pingmebot.task.Task;


/**
 * Represents a command for the user to add a task from the task list in the application.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Creates an AddCommand object with the specific task specified by the user.
     *
     * @param task User's specified task.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to update user's desired todo, deadline and events tasks to the task list, storage.
     * It also responds the corresponding reply to the user via the ui object.
     *
     * @param tasks The task list containing all the tasks which the user has added.
     * @param storage The local storage holding all the information about the entire task list.
     * @param ui The object responsible for displaying corresponding response to the user.
     * @throws PingMeException If an error occurs during the execution process.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws PingMeException {
        tasks.addTask(task);
        ui.additionToTasksText(task, tasks);
    }

    /**
     * Returns a boolean to see if the 2 AddCommand objects are the same by comparing the task that it has.
     *
     * @param obj The other command to be compared to.
     * @return A boolean to see if the 2 AddCommand objects are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AddCommand otherCommand = (AddCommand) obj;
        return this.task.equals(otherCommand.task);
    }
}
