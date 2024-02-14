package duke.command;
import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {

    private String description;
    private String startTime;
    private String endTime;

    /**
     * Constructs an EventCommand given the description, start time, and end time.
     *
     * @param description Description of the event task.
     * @param startTime Start time of the event.
     * @param endTime End time of the event.
     */
    public EventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Executes the EventCommand by creating a new Event task,
     * adding it to the task list, displaying the task response
     * using Ui, and saving the updated task list using Storage.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event event;
        event = new Event(description, startTime, endTime);
        tasks.add(event);
        ui.addResponse(event, tasks);
        storage.saveList(tasks.getTasks());
    }
}
