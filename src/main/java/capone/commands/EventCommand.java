package capone.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;

import capone.Parser;
import capone.Storage;
import capone.TaskList;
import capone.exceptions.CaponeException;
import capone.exceptions.InsufficientArgumentException;
import capone.exceptions.InvalidCommandException;
import capone.tasks.Event;
import capone.ui.Ui;

/**
 * Represents a command to add an event task to the TaskList.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class EventCommand extends Command {

    /** List containing input parameters for EventCommand. */
    private final ArrayList<String> inputList;

    /**
     * Constructs an EventCommand with the specified input list.
     *
     * @param inputList The list containing input parameters for the EventCommand.
     */
    public EventCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    /**
     * Executes the EventCommand, adding a new event task to the TaskList.
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
            throw new InsufficientArgumentException("Insufficient arguments!\n"
                    + "Usage: event [description] /from [date] /to [date]");
        }

        int fromNdx = inputList.indexOf("/from");
        int toNdx = inputList.indexOf("/to");

        // If /to is specified before /from, throw error.
        if (toNdx < fromNdx) {
            throw new InvalidCommandException("Please input from date followed by to date!\n"
                    + "Usage: event [description] /from [date] /to [date]");
        }

        // The index of the word that is not found in a list would be -1.
        final int COMMAND_NOT_FOUND = -1;
        // The index of the words that is last of the list.
        final int LAST_WORD = inputList.size() - 1;
        // Catch potential errors from date entry.
        if (fromNdx == COMMAND_NOT_FOUND || fromNdx == LAST_WORD || toNdx == LAST_WORD) {
            throw new InsufficientArgumentException("Please enter a start and end date!\n"
                    + "Usage: event [description] /from [date] /to [date]");
        }

        // The starting index of the words that contain the description.
        final int STARTING_NDX_DESCRIPTION = 1;
        // The ending index of the words that contain the description.
        final int ENDING_NDX_DESCRIPTION = fromNdx;
        String description = Parser.parseDescription(STARTING_NDX_DESCRIPTION, ENDING_NDX_DESCRIPTION, inputList);

        if (description.equalsIgnoreCase("")) {
            throw new InsufficientArgumentException("Insufficient arguments!\n"
                    + "Usage: event [description] /from [date] /to [date]");
        }

        LocalDateTime fromDateTime = Parser.parseDateTime(fromNdx + 1, toNdx, inputList);
        LocalDateTime toDateTime = Parser.parseDateTime(toNdx + 1, inputList.size(), inputList);

        assert (fromDateTime != null) && (toDateTime != null) : "/from and /to DateTime should not be null";

        taskList.addTask(new Event(description.toString(), false, fromDateTime, toDateTime));

        storage.writeTasksToJsonFile(taskList);
        return ui.sendEvent(taskList);
    }
}
