package pingmebot.command;

import pingmebot.PingMeException;
import pingmebot.Storage;
import pingmebot.TaskList;
import pingmebot.UI;

/**
 * Represents a command for the user to un-mark a task from the task list in the application.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a UnmarkCommand object to allow user to un-mark the task in his/her task list as un-completed.
     *
     * @param taskNumber The number corresponding to the task in the list the user wants to un-mark as un-completed.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to allow user to unmark a task as un-completed and updating the local storage thereafter.
     * It also responds the corresponding reply to the user via the ui object.
     *
     * @param tasks The task list containing all the tasks which the user has added.
     * @param storage The local storage holding all the information about the entire task list.
     * @param ui The object responsible for displaying corresponding response to the user.
     * @throws PingMeException If an error occurs during the execution process.
     */
    public void execute(TaskList tasks, Storage storage, UI ui) throws PingMeException {
        tasks.updateTaskToStorage(storage);
        ui.unmarkTaskText(taskNumber, tasks);
    }

    /**
     * Returns a boolean to see if the 2 UnmarkCommand objects are the same by comparing the task number that it holds.
     *
     * @param obj The other command to be compared to.
     * @return A boolean to see if the 2 UnmarkCommand objects are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        UnmarkCommand otherCommand = (UnmarkCommand) obj;
        return this.taskNumber == otherCommand.taskNumber;
    }
}
