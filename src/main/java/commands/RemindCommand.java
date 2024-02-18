package commands;

import storage.Storage;
import task.TaskList;

/**
 *  Represents the command used to display the list of tasks to the user.
 *  The command checks if the task list is empty and provides appropriate feedback to the user.
 *
 */
public class RemindCommand extends Command {
    public static final String COMMAND_WORD = "remind";
    private static final String SUCCESS_MESSAGE = "Congrats, you have no upcoming tasks! Uncle is proud of you!";
    /**
     * Executes the RemindCommand, listing out all the tasks in the task list.
     * @param tasks   The TaskList representing the collection of tasks.
     * @param storage The Storage object handling storage operations.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return SUCCESS_MESSAGE;
        } else {
            return tasks.upcomingTasks();
        }
    }
}
