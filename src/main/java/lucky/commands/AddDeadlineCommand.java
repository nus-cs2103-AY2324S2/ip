package lucky.commands;

import java.io.IOException;
import java.util.ArrayList;

import lucky.common.Utils;
import lucky.storage.Storage;
import lucky.tasks.Deadline;
import lucky.tasks.Task;
import lucky.ui.Ui;


/**
 * The AddDeadlineCommand class is responsible for executing the command to add a deadline task to a
 * list of tasks.
 */
public class AddDeadlineCommand extends Command {

    /**
     * Adds a deadline task to a list of tasks, validates the input format, updates the storage, and
     * prints the updated task list.
     *
     * @param tasks An ArrayList of Task objects, representing the current list of tasks.
     * @param input A String[] that contains the details of the task being executed.
     * @throws CommandException for invalid input.
     */
    @Override
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        String pattern = "([^/]+)\\s+/by\\s+(\\d{1,2}/\\d{1,2}/\\d{4}\\s+\\d{4})";

        if (!isValidCommand(pattern, input)) {
            throw new CommandException(
                    "Wrong format! (format: deadline <your task> /by <dd/MM/yyyy HHmm>)");
        }

        String[] deadlineDetails = input[1].split("/by");

        if (!Utils.isValidDateTimeFormat(deadlineDetails[1])) {
            throw new CommandException(
                    "Datetime is in the wrong format. (format: deadline <your task> /by <dd/MM/yyyy HHmm>)");
        }

        Deadline deadlineTask = new Deadline(deadlineDetails[0].trim(),
                Utils.parseDateTime(deadlineDetails[1].trim()));

        tasks.add(deadlineTask);

        Storage.writeToStorage(deadlineTask);

        super.commandResponse = Ui.printOutput("Got it. I've added this task:\n" + deadlineTask.toString(),
                "Now you have " + tasks.size() + " tasks in the list.");
    }

}
