package duke.command;

import java.time.LocalDateTime;

import duke.TaskList;
import duke.task.Task;


/**
 * Deadline.
 */
public class DeadlineCommand extends Command {

    private final String event;
    private final LocalDateTime dueBy;

    /**
     * Constructor.
     *
     * @param event Name of the event.
     * @param dueBy Deadline of the event.
     */
    public DeadlineCommand(String event, LocalDateTime dueBy) {
        this.dueBy = dueBy;
        this.event = event;
    }

    /**
     * Adds a deadline event to the task list.
     *
     * @param tasks   The list of tasks.
     * @return Information about the deadline event.
     */
    @Override
    public String execute(TaskList tasks) {
        assert tasks != null;
        Task task = new duke.task.Deadline(this.event, this.dueBy);
        tasks.add(task);
        return tasks.standardize(task);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DeadlineCommand)) {
            return false;
        }
        DeadlineCommand otherCommand = (DeadlineCommand) other;
        return this.event.equals(otherCommand.event)
                && this.dueBy.equals(otherCommand.dueBy);
    }
}
