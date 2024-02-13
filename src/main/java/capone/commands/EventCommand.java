package capone.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        // Catch potential errors from date entry.
        if (fromNdx == -1 || toNdx == -1 || toNdx - fromNdx == 1 || fromNdx - toNdx == 1
                || fromNdx == inputList.size() - 1 || toNdx == inputList.size() - 1) {
            throw new InsufficientArgumentException("Please enter a start and end date!\n"
                    + "Usage: event [description] /from [date] /to [date]");
        }

        // Combine the task description into a single string.
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < fromNdx; i++) {
            if (i == fromNdx - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        if (description.toString().equalsIgnoreCase("")) {
            throw new InsufficientArgumentException("Insufficient arguments!\n"
                    + "Usage: event [description] /from [date] /to [date]");
        }

        LocalDate fromDate = null;
        LocalTime fromTime = null;
        // Process input for the deadline (i.e. after the /by command).
        StringBuilder fromDateString = new StringBuilder();
        for (int i = fromNdx + 1; i < toNdx; i++) {
            if (Parser.isDateFormat(inputList.get(i))) {
                fromDate = Parser.parseDate(inputList.get(i));
                continue;
            }

            if (Parser.isTimeFormat(inputList.get(i))) {
                fromTime = Parser.parseTime(inputList.get(i));
                continue;
            }

            // If this is the last word to be added.
            if (i == inputList.size() - 1) {
                fromDateString.append(inputList.get(i));
            } else {
                fromDateString.append(inputList.get(i)).append(" ");
            }
        }

        LocalDate toDate = null;
        LocalTime toTime = null;
        // Process input for the deadline (i.e. after the /by command).
        StringBuilder toDateString = new StringBuilder();
        for (int i = toNdx + 1; i < inputList.size(); i++) {
            if (Parser.isDateFormat(inputList.get(i))) {
                toDate = Parser.parseDate(inputList.get(i));
                continue;
            }

            if (Parser.isTimeFormat(inputList.get(i))) {
                toTime = Parser.parseTime(inputList.get(i));
                continue;
            }

            // If this is the last word to be added, don't add trailing whitespace.
            if (i == inputList.size() - 1) {
                toDateString.append(inputList.get(i));
            } else {
                toDateString.append(inputList.get(i)).append(" ");
            }
        }

        LocalDateTime fromDateTime = Parser.processDateTime(fromDate, fromTime);
        LocalDateTime toDateTime = Parser.processDateTime(toDate, toTime);

        // If user entered both valid date and time.
        if (fromDateTime != null && toDateTime != null) {
            taskList.addTask(new Event(description.toString(), false, fromDateTime, toDateTime));
        } else if (fromDateTime != null || toDateTime != null) {
            // If either fromDateTime or toDateTime is null but the other is not.
            throw new InvalidCommandException("Oops! It seems like there is a format mismatch between"
                    + "your start and dates and end dates.\nMake sure you enter both of them in the accepted "
                    + "date format!\nAlternatively, you can specify a string for both your start and end dates.\n"
                    + "Use the 'help' command for more information.");
        } else {
            assert (fromDateTime != null) && (toDateTime != null) : "User has at least one valid date entry."
                    + "String entry should not be created for this task.";
            taskList.addTask(new Event(description.toString(), false,
                    fromDateString.toString(), toDateString.toString()));
        }

        storage.writeTasksToJsonFile(taskList);

        return ui.sendEvent(taskList);
    }
}
