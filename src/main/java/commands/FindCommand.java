package commands;

import exceptions.ChatBotException;
import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Represents a command to find tasks with a specific description.
 * Extends the Command class and implements the execute method to execute the command.
 */
public class FindCommand extends Command {
    private String taskDescription;

    /**
     * Constructs a FindCommand object with the specified task description to search for.
     *
     * @param taskDescription The description of the task to search for.
     */
    public FindCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the find command to search for tasks with the specified description.
     *
     * @param taskList The TaskList containing the current tasks.
     * @param ui       The Ui instance for user interaction and output.
     * @param storage  The Storage instance for saving tasks or loading data.
     * @return A UserCommand containing the list of tasks matching the description, or an error message if no matches are found.
     */
    @Override
    public UserCommand execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            TaskList matchingTasks = taskList.findTasks(this.taskDescription);
            return new UserCommand("\tHere are the matching tasks in your list: ", matchingTasks.listTasks());
        } catch (ChatBotException e) {
            return new UserCommand("\tOops! No matching tasks found.");
        }
    }
}
