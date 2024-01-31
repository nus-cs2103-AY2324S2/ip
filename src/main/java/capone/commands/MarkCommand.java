package capone.commands;

import capone.Storage;
import capone.TaskList;
import capone.Ui;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import capone.exceptions.InvalidIndexException;
import capone.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a command to mark a task as completed in the TaskList.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class MarkCommand extends Command {

    /** List containing input parameters for MarkCommand. */
    private final ArrayList<String> inputList;

    /**
     * Constructs a MarkCommand with the specified input list.
     *
     * @param inputList The list containing input parameters for the MarkCommand.
     */
    public MarkCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    /**
     * Executes the MarkCommand, marking a task as completed in the TaskList.
     *
     * @param taskList The TaskList to be updated.
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data.
     * @throws CaponeException If any Capone-related exception occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        if (inputList.size() == 1) {
            throw new InsufficientArgumentException("Please enter an index of a task you'd like to mark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: mark [index]");
        } else if (inputList.size() > 2) {
            throw new InvalidIndexException("Please enter only one index you would like to mark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: mark [index]");
        }

        // Mark task as done.
        try {
            Task markedTask = taskList.getTask(Integer.parseInt(inputList.get(1)) - 1);
            markedTask.markTask();
            storage.writeTasksToJsonFile(taskList);

            // Inform the user that the task has been marked.
            ui.sendMessage("Nice! I've marked this task as done:\n" + markedTask + "\n");
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidIndexException("Sorry, you have entered an invalid index.\n" +
                    "You can check the list of valid indices using the 'list' command.");
        }
    }
}
