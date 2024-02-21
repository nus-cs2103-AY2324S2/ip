package capone.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;

import capone.Parser;
import capone.Storage;
import capone.TaskList;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import capone.tasks.Deadline;
import capone.ui.Ui;

/**
 * Represents a command to add a deadline task to TaskList.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class DeadlineCommand extends Command {
    /**
     * Constant string that provides information to the user on how to use the command.
     */
    private static final String USAGE_STRING = "Usage: deadline [description] /by [date] [time]";

    /** List containing input parameters for DeadlineCommand. */
    private final ArrayList<String> inputList;

    /**
     * Constructs a DeadlineCommand with the specified input list.
     *
     * @param inputList The list containing input parameters for the DeadlineCommand.
     */
    public DeadlineCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    /**
     * Executes the DeadlineCommand, adding a new deadline task to the TaskList.
     *
     * @param taskList The TaskList to be updated.
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data.
     * @return The String output of the bot after executing the user's command.
     * @throws CaponeException If any Capone-related exception occurs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        // If the inputList has only one string, throw error (insufficient args).
        if (inputList.size() == 1) {
            throw new InsufficientArgumentException("Insufficient arguments!\n" + DeadlineCommand.USAGE_STRING);
        }

        // Find the index of the /by command.
        int byNdx = inputList.indexOf("/by");

        // Catch potential errors from date entry.
        if (byNdx == inputList.size() - 1 || byNdx == -1) {
            throw new InsufficientArgumentException("Please enter a date for this deadline task!\n"
                    + DeadlineCommand.USAGE_STRING);
        }

        // The starting index of the words that contain the description.
        final int startingNdxDescription = 1;
        // The ending index of the words that contain the description.
        final int endingNdxDescription = byNdx;
        String description = Parser.parseDescription(startingNdxDescription, endingNdxDescription, inputList);

        if (description.toString().equalsIgnoreCase("")) {
            throw new InsufficientArgumentException("Insufficient arguments!\n"
                    + DeadlineCommand.USAGE_STRING);
        }

        LocalDateTime byDateTime = Parser.parseDateTime(byNdx + 1, inputList.size(), inputList);

        assert byDateTime != null : "byDateTime should not be null";

        taskList.addTask(new Deadline(description, false, byDateTime));

        storage.writeTasksToJsonFile(taskList);

        return ui.sendDeadline(taskList);
    }
}
