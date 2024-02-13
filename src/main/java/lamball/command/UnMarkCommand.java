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
        assert idx > 0 : "index should be non-negative and non-zero";
        assert idx <= taskList.size() : "index should be within the bound of task list";

        taskList.unMark(idx);
        return true;
    }
}
