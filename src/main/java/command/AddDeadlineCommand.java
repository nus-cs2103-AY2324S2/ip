package command;

import duke.DateTimeManager;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import task.Deadline;
import task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A AddDeadlineCommand class to add new Deadline task.
 * A subclass of Command class.
 */
public class AddDeadlineCommand extends Command {

    private String input;

    /**
     * Creates a constructor with userInput as argument.
     * @param userInput
     */
    public AddDeadlineCommand(String userInput) {
        this.input = userInput;
    }

    /**
     * Adds new Deadline task.
     * Performs some prior checks to ensure the validity of the new Deadline.
     * If invalid input occurs, error message is returned.
     *
     * @param tasks Task Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws DukeException If there is missing description or invalid date and time for 'by'.
     */
    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("Missing the description!");
        }
        String[] deadlineSplit = input.split("/");

        if (deadlineSplit.length < 2) {
            throw new DukeException("Invalid format for new Deadline!");
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
