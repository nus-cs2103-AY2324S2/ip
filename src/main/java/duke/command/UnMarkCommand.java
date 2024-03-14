package duke.command;

import duke.TaskList;
import duke.task.Task;

/**
 * Representation of a user's *unmark* command.
 */
public class UnMarkCommand extends Command {

    private final int idx;

    public UnMarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public String execute(TaskList tasks) {
        assert tasks != null;
        Task task = tasks.get(idx);
        task.unmark();
        return "OK, I've marked this task as not done yet:\n" + task + "\n";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UnMarkCommand)) {
            return false;
        }
        UnMarkCommand otherCommand = (UnMarkCommand) other;
        return this.idx == otherCommand.idx;
    }
}
