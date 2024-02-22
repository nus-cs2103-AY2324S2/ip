package lucky.commands;

import java.io.IOException;
import java.util.ArrayList;

import lucky.common.Utils;
import lucky.storage.Storage;
import lucky.tasks.Event;
import lucky.tasks.Task;
import lucky.ui.Ui;

/**
 * The AddEventCommand class is responsible for executing the command to add an event task to a list
 * of tasks.
 */
public class AddEventCommand extends Command {

    /**
     * Adds an event task to a list of tasks, validates the input format, update the storage, and prints
     * the updated task list.
     *
     * @param tasks An ArrayList of Task objects, representing the current list of tasks.
     * @param input The input parameter is an array of strings that represents the user's input. It
     *              contains the details of the event task.
     * @throws CommandException for invalid input.
     */
    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        // This regex pattern was generated using ChatGPT. Generating regex pattern through ChatGPT
        // can allow developers to focus more on less mundane stuff.
        String pattern =
                "([^/]+)\\s+/from\\s+(\\d{1,2}/\\d{1,2}/\\d{4}"
                        + "\\s+\\d{4})\\s+/to\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})";

        if (!isValidCommand(pattern, input)) {
            throw new CommandException(
                    "Wrong format! (format: event <your task> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>)");
        }

        String[] eventDetails = input[1].split("/from|/to");

        if (!Utils.isValidDateTimeFormat(eventDetails[1], eventDetails[2])) {
            throw new CommandException(
                    "Datetime is in the wrong format. (format: event <your task>"
                            + " /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>)");
        }

        Event eventTask = new Event(eventDetails[0].trim(),
                Utils.parseDateTime(eventDetails[1].trim()),
                Utils.parseDateTime(eventDetails[2].trim()));
        tasks.add(eventTask);

        Storage.writeToStorage(eventTask);

        super.commandResponse = Ui.printOutput("Got it. I've added this task:\n" + eventTask.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

}
