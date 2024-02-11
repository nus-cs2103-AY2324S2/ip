package duke.command;

import duke.TaskList;
import duke.task.Task;

/**
 * Marks a task.
 */
public class MarkCommand extends Command {

    private final int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Mark a task.
     *
     * @param tasks   The list of tasks.
     * @return Which task was marked.
     */
    @Override
    public String execute(TaskList tasks) {
        Task task = tasks.get(this.idx);
        task.mark();
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MarkCommand otherCommand)) {
            return false;
        }
        return this.idx == otherCommand.idx;
    }
}
