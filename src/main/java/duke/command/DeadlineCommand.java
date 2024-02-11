package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
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
     * @param storage Storage interface for persistence.
     * @return Information about the deadline event.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        Task task = new duke.task.Deadline(this.event, this.dueBy);
        tasks.add(task);
        return tasks.standardize(task);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DeadlineCommand otherCommand)) {
            return false;
        }
        return this.event.equals(otherCommand.event)
                && this.dueBy.equals(otherCommand.dueBy);
    }
}
