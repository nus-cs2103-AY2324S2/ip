package jerry.command;

import jerry.Event;
import jerry.TaskList;
import jerry.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a new Event task to the task list.
 * <p>
 * Event tasks are characterized by a start and end time, along with a description. This command
 * enables the addition of event tasks to the task list, enhancing the application's versatility in handling various task types.
 */
public class AddEventCommand extends Command {

    private final String commandDetails;

    /**
     * Constructs an {@code AddEventCommand} with the specified UI, task list, description, start time, and end time.
     *
     * @param ui          The UI component for interacting with the user.
     * @param tasks       The task list to which the new event task will be added.
     * @param commandDetails The description of the event task.
     */
    public AddEventCommand(Ui ui, TaskList tasks, String commandDetails) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.commandDetails = commandDetails;

    }

    /**
     * Executes the addition of an Event task to the task list.
     * <p>
     * An Event task with the provided description, start time, and end time is created and added to the task list.
     * The user is then notified of the task's successful addition.
     */
    @Override
    public String execute() {
        try {
            if (!commandDetails.contains(" /from ") || !commandDetails.contains(" /to ")) {
                throw new CommandFormatException("Wrong format, please include start and end time "
                        + "\nUsage: event <task description> /from <start time> /to <end time>");
            }
            String[] eventParts = commandDetails.split(" /from ", 2);
            String[] fromTo = eventParts[1].split(" /to ", 2);
            String description = eventParts[0];
            String start = fromTo[0];
            String end = fromTo[1];
            Event event = new Event(description, start, end);
            if (!event.dateTimeIsNull()) {
                tasks.addTask(event);
                return ui.showAdded(event, tasks);
            } else {
                return ui.showWrong();
            }
        } catch (DateTimeParseException dateTimeException) {
            return "Invalid date/time format. Please use yyyy-MM-dd HH:mm.";
        } catch (CommandFormatException e) {
            return ui.showMessage(e.getMessage());
        }
    }
}
