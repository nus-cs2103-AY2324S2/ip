package command;

import task.TaskList;
import utilities.Storage;

/**
 * Controls what to do when user wants to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * ListCommand class constructor.
     */
    public ListCommand() {
        super(false);
    }
    /**
     * Executes the listing process of all tasks in the task list.
     * @param taskList The task list that the command is applied to.
     * @param storage The storage that the task list is stored in after the command is applied.
     * @return The response expected from the chatbot.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.listToString() + taskList.numTaskToString();
    }
}
