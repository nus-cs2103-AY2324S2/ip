package lamball.command;

import lamball.TaskList;

/**
 * Command that unmarks a given task.
 */
public class UnMarkCommand extends Command {
    private int idx;
    private TaskList taskList;

    /**
     * Constructor for unmark command.
     *
     * @param idx Index of list to unmark.
     * @param tasks TaskList to unmark from.
     */
    public UnMarkCommand(int idx, TaskList tasks) {
        this.taskList = tasks;
        this.idx = idx;
    }

    @Override
    public boolean run() {
        assert idx > 0;
        assert idx <= taskList.size();

        taskList.unMark(idx);
        return true;
    }
}
