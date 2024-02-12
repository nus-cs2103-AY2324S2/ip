package lamball.command;

import lamball.TaskList;

public class MarkCommand extends Command {
    private boolean isInit;
    private int idx;

    public MarkCommand(int idx, TaskList tasks, boolean isInit) {
        super(tasks);
        this.idx = idx;
        this.isInit = isInit;
    }

    @Override
    public boolean run() {
        taskList.mark(idx, this.isInit);
        return true;
    }
}
