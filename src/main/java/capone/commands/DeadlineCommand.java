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
import capone.tasks.Deadline;
import capone.ui.Ui;

/**
 * Represents a command to add a deadline task to TaskList.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class DeadlineCommand extends Command {

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
            throw new InsufficientArgumentException("Insufficient arguments!\n"
                    + "Usage: deadline [description] /by [date]");
        }

        // Find the index of the /by command.
        int byNdx = inputList.indexOf("/by");

        // Catch potential errors from date entry.
        if (byNdx == inputList.size() - 1 || byNdx == -1) {
            throw new InsufficientArgumentException("Please enter a date for this deadline task!\n"
                    + "Usage: deadline [description] /by [date]");
        }

        // Combine description of task into one string.
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < byNdx; i++) {
            if (i == byNdx - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        if (description.toString().equalsIgnoreCase("")) {
            throw new InsufficientArgumentException("Insufficient arguments!\n"
                    + "Usage: deadline [description] /by [date]");
        }

        LocalDate date = null;
        LocalTime time = null;
        // Process input for the deadline (i.e. after the /by command).
        StringBuilder byDate = new StringBuilder();
        for (int i = byNdx + 1; i < inputList.size(); i++) {
            if (Parser.isDateFormat(inputList.get(i))) {
                date = Parser.parseDate(inputList.get(i));
                continue;
            }

            if (Parser.isTimeFormat(inputList.get(i))) {
                time = Parser.parseTime(inputList.get(i));
                continue;
            }

            // If this is the last word to be added.
            if (i == inputList.size() - 1) {
                byDate.append(inputList.get(i));
            } else {
                byDate.append(inputList.get(i)).append(" ");
            }
        }

        LocalDateTime deadlineDateTime = Parser.processDateTime(date, time);

        if (deadlineDateTime != null) {
            taskList.addTask(new Deadline(description.toString(), false, deadlineDateTime));
        } else {
            taskList.addTask(new Deadline(description.toString(), false, byDate.toString()));
        }

        storage.writeTasksToJsonFile(taskList);

        return ui.sendDeadline(taskList);
    }
}
