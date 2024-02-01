package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DateTimeManager;
import task.Event;
import task.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A AddEventCommand class to add new Event task.
 * A subclass of Command class.
 */
public class AddEventCommand extends Command {

    /** The information from the user to create new Event. */
    private String input;

    /**
     * Creates a constructor with userInput as argument.
     *
     * @param userInput The information when creating new Event.
     */
    public AddEventCommand(String userInput) {
        this.input = userInput;
    }

    /**
     * Adds new Event task.
     * Performs some prior checks to ensure the validity of the new Event.
     * If invalid input occurs, error message is returned.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws DukeException If there is missing description or invalid date and time for 'start' and 'end'.
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("Missing the description!");
        }
        String[] eventSplit = input.split("/");
        if (eventSplit.length < 3) {
            throw new DukeException("Invalid format for new Event!");
        }
        String name = eventSplit[0].substring(6).trim();
        String start = eventSplit[1].substring(5).trim();
        String end = eventSplit[2].substring(3).trim();
        LocalDateTime startDT = DateTimeManager.convertStringToLocalDateTime(start);
        LocalDateTime endDT = DateTimeManager.convertStringToLocalDateTime(end);
        Event newEvent = new Event(name, false, startDT, endDT);
        tasks.addTask(newEvent);
        ArrayList<Task> newEventList = new ArrayList<>();
        newEventList.add(newEvent);
        storage.writeArrayListToFile(newEventList, false);
        ui.printAnyStatement("Got it. I've added this task:");
        ui.printAnyStatement(newEvent.toString());
        ui.printAnyStatement("Now you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
