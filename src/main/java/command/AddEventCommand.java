package command;
import sky.Ui;
import task.Event;
import task.TaskList;

/**
 * Represents a command to add an event task to the task list.
 */
public class AddEventCommand extends Command {
    private String description;
    private String start;
    private String end;

    /**
     * Constructor for AddEventCommand.
     * @param description Description of the event.
     * @param start Start date and time of the event.
     * @param end End date and time of the event.
     */
    public AddEventCommand(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Adds an event task to the task list and shows the user the added task.
     * @param tasks Task list to add the event task to.
     * @param ui Ui to display the added task to the user.
     * @return String to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Event event = new Event(description, start, end);
        tasks.add(event);
        return ui.showAddTask(event, tasks.size());
    }
}
