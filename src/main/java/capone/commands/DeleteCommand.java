package capone.commands;

import java.util.ArrayList;

import capone.Storage;
import capone.TaskList;
import capone.Ui;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import capone.exceptions.InvalidIndexException;
import capone.tasks.Task;

/**
 * Represents a command to delete a task from the TaskList.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class DeleteCommand extends Command {

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
     * @throws CaponeException If any Capone-related exception occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        // If the inputList has more than two arguments, throw exception.
        if (inputList.size() == 1) {
            throw new InsufficientArgumentException("Please enter an index of a task you'd like to delete.\n"
                    + "You can view all tasks using the 'list' command.\n"
                    + "Usage: delete [index]");
        } else if (inputList.size() > 2) {
            throw new InvalidIndexException("Please enter only one index you would like to delete.\n"
                    + "You can view all tasks using the 'list' command.\n"
                    + "Usage: delete [index]");
        }

        try {
            // Remove the task from the tasks ArrayList.
            Task removedTask = taskList.removeTask(Integer.parseInt(inputList.get(1)) - 1);

            storage.writeTasksToJsonFile(taskList);

            ui.sendMessage(String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n",
                    removedTask.toString(), taskList.getSize()));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidIndexException("Sorry, you have entered an invalid index.\n"
                    + "You can check the list of valid indices using the 'list' command.");
        }
    }
}
