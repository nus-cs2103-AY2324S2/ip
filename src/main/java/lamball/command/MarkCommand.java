package lamball.command;

import lamball.TaskList;

/**
 * Command that unmarks a given task.
 */
public class MarkCommand extends Command {
    private TaskList taskList;
    private boolean isInit;
    private int idx;

    /**
     * Constructor for mark command.
     *
     * @param idx Index of list to mark.
     * @param tasks TaskList to mark from.
     * @param isInit Whether this command is run during initialization.
     */
    public MarkCommand(int idx, TaskList tasks, boolean isInit) {
        this.taskList = tasks;
        this.idx = idx;
        this.isInit = isInit;
    }

    @Override
    public boolean run() {
        taskList.mark(idx, this.isInit);
        return true;
    }
}
