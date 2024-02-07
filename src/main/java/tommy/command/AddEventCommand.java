package tommy.command;

import tommy.Ui;
import tommy.Parser;
import tommy.Storage;

import tommy.task.Task;
import tommy.task.TaskList;
import tommy.task.Event;

import tommy.exception.InvalidArgumentException;

/**
 * Represents the command to add an Event task to the taskList.
 */
public class AddEventCommand extends Command {
    private String description;

    /**
     * Constructor for an AddEvent command,
     * which initialises the command with its task description.
     *
     * @param description Description of the Event task.
     */
    public AddEventCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            String[] components = this.description.split(" /from | /to ", 3);
            String eventDetails = components[0];
            String fromDate = components[1];
            String toDate = components[2];

            // format the dates
            String formattedFromDate = Parser.formatDate(fromDate);
            String formattedToDate = Parser.formatDate(toDate);

            // instantiate event
            String formattedDescription = eventDetails + " (from: " + formattedFromDate + " to: " + formattedToDate + ")";
            Task event = new Event(formattedDescription);

            taskList.addTask(event);
            Storage.save(taskList);
            ui.displayNewTask(event, taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("EVENT");
        }
    }
}
