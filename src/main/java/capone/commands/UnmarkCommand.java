package capone.commands;

import java.util.ArrayList;

import capone.Storage;
import capone.TaskList;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import capone.exceptions.InvalidIndexException;
import capone.tasks.Task;
import capone.ui.Ui;

/**
 * Represents a command to unmark a completed task in the TaskList.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class UnmarkCommand extends Command {
    /**
     * Constant string that provides information to the user on how to use the command.
     */
    private static final String USAGE_STRING = "Usage: unmark [index]";

    /** List containing input parameters for UnmarkCommand. */
    private final ArrayList<String> inputList;

    /**
     * Constructs an UnmarkCommand with the specified input list.
     *
     * @param inputList The list containing input parameters for the UnmarkCommand.
     */
    public UnmarkCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    /**
     * Executes the UnmarkCommand, unmarking a completed task in the TaskList.
     *
     * @param taskList The TaskList to be updated.
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data.
     * @return The String output of the bot after executing the user's command.
     * @throws CaponeException If any Capone-related exception occurs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        if (inputList.size() == 1) {
            throw new InsufficientArgumentException("Please enter an index of a task you'd like to unmark.\n"
                    + "You can view all tasks using the 'list' command.\n"
                    + UnmarkCommand.USAGE_STRING);
        } else if (inputList.size() > 2) {
            throw new InsufficientArgumentException("Please enter only one index you would like to unmark.\n"
                    + "You can view all tasks using the 'list' command.\n"
                    + UnmarkCommand.USAGE_STRING);
        }

        try {
            Task unmarkedTask = taskList.getTask(Integer.parseInt(inputList.get(1)) - 1);
            unmarkedTask.unmarkTask();
            storage.writeTasksToJsonFile(taskList);

            // Inform the user that the task has been marked.
            return ui.sendUnmark(unmarkedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidIndexException("Sorry, you have entered an invalid index.\n"
                    + "You can check the list of valid indices using the 'list' command.\n"
                    + UnmarkCommand.USAGE_STRING);
        }
    }
}
