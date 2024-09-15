package capone.commands;

import java.util.ArrayList;

import capone.Parser;
import capone.Storage;
import capone.TaskList;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import capone.exceptions.InvalidIndexException;
import capone.tasks.Task;
import capone.ui.Ui;


/**
 * Represents a command to update various fields of a Task.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class UpdateCommand extends Command {
    /**
     * Constant string that provides information to the user on how to use the command.
     */
    private static final String USAGE_STRING = "Usage: update [index] [new description]";

    /** List containing input parameters for MarkCommand. */
    private final ArrayList<String> inputList;

    /**
     * Constructs a MarkCommand with the specified input list.
     *
     * @param inputList The list containing input parameters for the MarkCommand.
     */
    public UpdateCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    /**
     * Executes the MarkCommand, marking a task as completed in the TaskList.
     *
     * @param taskList The TaskList to be updated.
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data.
     * @return The String output of the bot after executing the user's command.
     * @throws CaponeException If any Capone-related exception occurs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        if (inputList.size() < 3) {
            throw new InsufficientArgumentException("Insufficient arguments.\n"
                    + "You can view all tasks and their respective indices using the 'list' command.\n"
                    + UpdateCommand.USAGE_STRING);
        }

        try {
            // Index of the task in the TaskList object (zero-indexed).
            final int taskIndex = Integer.parseInt(inputList.get(1)) - 1;
            Task editTaskDesc = taskList.getTask(taskIndex);

            // The starting index of the word(s) that contain the new description.
            final int descriptionNdx = 2;

            // Get the new description from the user and update the task's description.
            String newDescription = Parser.parseDescription(descriptionNdx, inputList.size(), inputList);
            editTaskDesc.setDescription(newDescription);

            // Update the task list file.
            storage.writeTasksToJsonFile(taskList);

            // Inform the user that the task description has been updated.
            return ui.sendUpdate(editTaskDesc);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidIndexException("Sorry, you have entered an invalid index.\n"
                    + "You can check the list of valid indices using the 'list' command.\n"
                    + UpdateCommand.USAGE_STRING);
        }
    }
}
