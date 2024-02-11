package duke.command;

import duke.TaskList;
import duke.task.Task;

/**
 * Delete an event.
 */
public class DeleteCommand extends Command {

    private final int idx;

    /**
     * Constructor.
     *
     * @param idx Zero-based index of task to delete.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Deletes an event from the Task list.
     *
     * @param tasks   The list of tasks.
     * @return Information about the task removed.
     */
    @Override
    public String execute(TaskList tasks) {
        assert tasks != null;
        Task task = tasks.get(idx);
        tasks.remove(idx);
        return "Noted. I've removed this task:\n" + task + "\n" + tasks.trailer() + "\n";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DeleteCommand)) {
            return false;
        }
        DeleteCommand otherCommand = (DeleteCommand) other;
        return this.idx == otherCommand.idx;
    }
}
