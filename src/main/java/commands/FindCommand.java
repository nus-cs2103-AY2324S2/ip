package commands;

import exceptions.ChatBotException;
import util.Storage;
import util.TaskList;
import util.Ui;

/**
 * Represents a command to find tasks with a specific description.
 */
public class FindCommand extends Command {
    private String taskDescription;

    /**
     * Constructs a FindCommand with the specified task description to search for.
     *
     * @param taskDescription The description of the task to search for.
     */
    public FindCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the find command to search for tasks with the specified description.
     *
     * @param taskList The list of tasks to search within.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage component for saving task data.
     */
    @Override
    public UserCommand execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            TaskList matchingTasks = taskList.findTasks(this.taskDescription);
            return new UserCommand("\tHere are the list of tasks: ", matchingTasks.listTasks());
        } catch (ChatBotException e) {
            System.out.println("Oops! No matching tasks found.");
            return new UserCommand("Oops! No matching tasks found.");
        }
    }
}
