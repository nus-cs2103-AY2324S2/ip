package command;

import java.time.LocalDateTime;
import java.util.ArrayList;

import andelu.AndeluException;
import andelu.DateTimeManager;
import andelu.PriorityLevel;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;
import task.Event;
import task.Task;



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
     * Returns the response from Andelu.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @return The response from Andelu.
     * @throws AndeluException If there is missing description or invalid date and time for 'start' and 'end'.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException {
        assert input != null : "input should not be null";
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new AndeluException("Missing the description!");
        }
        String[] eventSplit = input.split("/");
        if (eventSplit.length < 3) {
            throw new AndeluException("Invalid format for new Event!");
        }

        String[] priorityStringSplit = input.split("/priority");
        PriorityLevel priorityLevel = PriorityLevel.DEFAULT;
        if (priorityStringSplit.length == 2) {
            String priorityInput = priorityStringSplit[1].trim();
            if (priorityInput.equalsIgnoreCase("Low")) {
                priorityLevel = PriorityLevel.LOW;
            } else if (priorityInput.equalsIgnoreCase("Medium")) {
                priorityLevel = PriorityLevel.MEDIUM;
            } else if (priorityInput.equalsIgnoreCase("High")) {
                priorityLevel = PriorityLevel.HIGH;
            } else {
                throw new AndeluException("Please select the priority level: Low, Medium or High, if any.");
            }
        }

        String name = eventSplit[0].substring(6).trim();
        String start = eventSplit[1].substring(5).trim();
        String end = eventSplit[2].substring(3).trim();

        LocalDateTime startDT = DateTimeManager.convertStringToLocalDateTime(start);
        LocalDateTime endDT = DateTimeManager.convertStringToLocalDateTime(end);

        Event newEvent = new Event(name, false, priorityLevel, startDT, endDT);
        tasks.addTask(newEvent);
        ArrayList<Task> newEventList = new ArrayList<>();
        newEventList.add(newEvent);
        storage.writeArrayListToFile(newEventList, false);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it. I've added this task:\n");
        stringBuilder.append(newEvent.toString() + "\n");
        stringBuilder.append("Now you have " + tasks.getTasks().size() + " tasks in the list.\n");

        ui.printAnyStatement("Got it. I've added this task:");
        ui.printAnyStatement(newEvent.toString());
        ui.printAnyStatement("Now you have " + tasks.getTasks().size() + " tasks in the list.");
        return stringBuilder.toString();
    }
}
