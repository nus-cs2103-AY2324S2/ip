package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDateTime;

/**
 * Deadline.
 */
public class DeadlineCommand extends Command{

    private final String event;
    private final LocalDateTime dueBy;

    /**
     * Constructor.
     * @param event Name of the event.
     * @param dueBy Deadline of the event.
     */
    public DeadlineCommand(String event, LocalDateTime dueBy) {
        this.dueBy = dueBy;
        this.event = event;
    }

    /**
     * Adds a deadline event to the task list.
     * @param tasks The list of tasks.
     * @param ui UI interface with the user.
     * @param storage Storage interface for persistence.
     * @return Information about the deadline event.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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
