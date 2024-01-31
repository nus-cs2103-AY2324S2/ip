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
 * Represents a command to unmark a completed task in the TaskList.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class UnmarkCommand extends Command {

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
     * @throws CaponeException If any Capone-related exception occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        if (inputList.size() == 1) {
            throw new InsufficientArgumentException("Please enter an index of a task you'd like to unmark.\n"
                    + "You can view all tasks using the 'list' command.\n"
                    + "Usage: unmark [index]");
        } else if (inputList.size() > 2) {
            throw new InsufficientArgumentException("Please enter only one index you would like to unmark.\n"
                    + "You can view all tasks using the 'list' command.\n"
                    + "Usage: unmark [index]");
        }

        try {
            Task unmarkedTask = taskList.getTask(Integer.parseInt(inputList.get(1)) - 1);
            unmarkedTask.unmarkTask();
            storage.writeTasksToJsonFile(taskList);

            // Inform the user that the task has been marked.
            ui.sendMessage("OK, I've marked this task as not done yet:\n" + unmarkedTask + "\n");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidIndexException("Sorry, you have entered an invalid index.\n"
                    + "You can check the list of valid indices using the 'list' command.");
        }
    }
}
