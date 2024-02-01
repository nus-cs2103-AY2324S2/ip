package command;

import andelu.DateTimeManager;
import andelu.AndeluException;
import andelu.TaskList;
import andelu.Ui;
import andelu.Storage;

import task.Deadline;
import task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A AddDeadlineCommand class to add new Deadline task.
 * A subclass of Command class.
 */
public class AddDeadlineCommand extends Command {

    /** The information from the user to create new Deadline. */
    private String input;

    /**
     * Creates a constructor with userInput as argument.
     *
     * @param userInput The information when creating new Deadline.
     */
    public AddDeadlineCommand(String userInput) {
        input = userInput;
    }

    /**
     * Adds new Deadline task.
     * Performs some prior checks to ensure the validity of the new Deadline.
     * If invalid input occurs, error message is returned.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws AndeluException If there is missing description or invalid date and time for 'by'.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new AndeluException("Missing the description!");
        }
        String[] deadlineSplit = input.split("/");
        if (deadlineSplit.length < 2) {
            throw new AndeluException("Invalid format for new Deadline!");
        }
        String name = deadlineSplit[0].substring(9).trim();
        String by = deadlineSplit[1].substring(3).trim();

        LocalDateTime byDt = DateTimeManager.convertStringToLocalDateTime(by);
        Deadline newDeadline = new Deadline(name,false, byDt);
        tasks.addTask(newDeadline);
        ArrayList<Task> newDeadlineList = new ArrayList<>();
        newDeadlineList.add(newDeadline);
        storage.writeArrayListToFile(newDeadlineList, false);

        ui.printAnyStatement("Got it. I've added this task:");
        ui.printAnyStatement(newDeadline.toString());
        ui.printAnyStatement("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
