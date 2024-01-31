package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
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
     * @param ui      UI interface with the user.
     * @param storage Storage interface for persistence.
     * @return Which task was marked.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
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
