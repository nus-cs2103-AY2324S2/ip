package eggy.command;

import eggy.exception.DateTimeFormatException;
import eggy.exception.EggyException;
import eggy.exception.IncompleteTaskException;
import eggy.exception.SaveTasksException;
import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.task.Event;
import eggy.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a command to add an event to the task list.
 */
public class EventCommand extends Command {
    /** The name of the event. */
    private String name;
    /** The start time of the event. */
    private LocalDateTime start;
    /** The end time of the event. */
    private LocalDateTime end;

    /**
     * Constructs an EventCommand.
     *
     * @param commands The array of commands.
     * @throws IncompleteTaskException If the task description is incomplete.
     * @throws DateTimeFormatException If the date and time format is invalid.
     */
    public EventCommand(String[] commands) throws IncompleteTaskException, DateTimeFormatException {
        if (commands.length < 2) {
            throw new IncompleteTaskException("event");
        }
        String[] eventSplit = commands[1].split(" /from | /to ");
        if (eventSplit.length < 3) {
            throw new IncompleteTaskException("event");
        }
        this.name = eventSplit[0];
        this.start = Command.parseDateTime(eventSplit[1]);
        this.end = Command.parseDateTime(eventSplit[2]);
    }

    /**
     * Adds an event to the task list and saves the task list to the storage.
     *
     * @param tasks The task list of the chatbot.
     * @param ui The user interface of the chatbot.
     * @param storage The storage of the chatbot.
     * @throws SaveTasksException If there is an error saving the task list to the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveTasksException {
        Event newEvent = new Event(this.name, this.start, this.end);
        tasks.addTask(newEvent);
        ui.printTaskAdded(newEvent, tasks.getSize());
        storage.save(tasks);
    }
}
