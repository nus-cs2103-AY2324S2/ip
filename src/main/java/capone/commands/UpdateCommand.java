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
                    + "Usage: update [index] [new description]");
        }

        // Mark task as done.
        try {
            // Index of the task in the TaskList object (zero-indexed).
            final int TASK_INDEX = Integer.parseInt(inputList.get(1)) - 1;
            Task newTaskDesc = taskList.getTask(TASK_INDEX);

            // The starting index of the words that contain the description.
            final int DESC_NDX = 2;

            // Get the new description from the user and update the task's description.
            String newDescription = Parser.parseDescription(DESC_NDX, inputList.size(), inputList);
            newTaskDesc.setDescription(newDescription);

            // Update the task list file.
            storage.writeTasksToJsonFile(taskList);

            // Inform the user that the task has been marked.
            return ui.sendUpdate(newTaskDesc);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidIndexException("Sorry, you have entered an invalid index.\n"
                    + "You can check the list of valid indices using the 'list' command.");
        }
    }
}
