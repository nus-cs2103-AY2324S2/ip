package duke.command;

import java.time.LocalDateTime;

import duke.TaskList;
import duke.task.Task;


/**
 * Add an event.
 */
public class EventCommand extends Command {

    private final String event;
    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    /**
     * Constructor.
     *
     * @param event    The name of the event to add.
     * @param fromDate When the event starts.
     * @param toDate   When the event ends.
     */
    public EventCommand(String event, LocalDateTime fromDate, LocalDateTime toDate) {
        this.event = event;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Adds the event to the task list.
     *
     * @param tasks   The list of tasks.
     * @return The updated task list as a string.
     */
    @Override
    public String execute(TaskList tasks) {
        Task task = new duke.task.Event(this.event, this.fromDate, this.toDate);
        tasks.add(task);
        return tasks.standardize(task);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof EventCommand otherCommand)) {
            return false;
        }
        return this.event.equals(otherCommand.event)
                && this.fromDate.equals(otherCommand.fromDate)
                && this.toDate.equals(otherCommand.toDate);
    }
}
