package tommy.command;

import tommy.Parser;
import tommy.Storage;
import tommy.Ui;
import tommy.exception.InvalidArgumentException;
import tommy.task.Event;
import tommy.task.Task;
import tommy.task.TaskList;


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
    public String execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            String[] components = this.description.split(" /from | /to ", 3);
            String eventDetail = components[0];
            String fromDate = components[1];
            String toDate = components[2];

            String formattedFromDate = Parser.formatDate(fromDate);
            String formattedToDate = Parser.formatDate(toDate);

            String formattedDescription = eventDetail
                    + " (from: " + formattedFromDate + " to: " + formattedToDate + ")";

            Task event = new Event(formattedDescription);

            taskList.addTask(event);
            Storage.save(taskList);
            return ui.displayNewTask(event, taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("EVENT");
        }
    }
}
