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
 * Represents a command to delete a task from the TaskList.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class DeleteCommand extends Command {
    /**
     * Constant string that provides information to the user on how to use the command.
     */
    private static final String USAGE_STRING = "Usage: delete [index]";

    /** List containing input parameters for DeleteCommand. */
    private final ArrayList<String> inputList;

    /**
     * Constructs a DeleteCommand with the specified input list.
     *
     * @param inputList The list containing input parameters for the DeleteCommand.
     */
    public DeleteCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    /**
     * Executes the DeleteCommand, removing a task from the TaskList based on the provided index.
     *
     * @param taskList The TaskList to be updated.
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data.
     * @return The String output of the bot after executing the user's command.
     * @throws CaponeException If any Capone-related exception occurs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        // If the inputList has more than two arguments, throw exception.
        if (inputList.size() == 1) {
            throw new InsufficientArgumentException("Please enter an index of a task you'd like to delete.\n"
                    + "You can view all tasks using the 'list' command.\n" + DeleteCommand.USAGE_STRING);
        } else if (inputList.size() > 2) {
            throw new InvalidIndexException("Please enter only one index you would like to delete.\n"
                    + "You can view all tasks using the 'list' command.\n" + DeleteCommand.USAGE_STRING);
        }

        try {
            Task removedTask = taskList.removeTask(Integer.parseInt(inputList.get(1)) - 1);

            storage.writeTasksToJsonFile(taskList);

            return ui.sendDelete(taskList, removedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidIndexException("Sorry, you have entered an invalid index.\n"
                    + "You can check the list of valid indices using the 'list' command.\n"
                    + DeleteCommand.USAGE_STRING);
        }
    }
}
