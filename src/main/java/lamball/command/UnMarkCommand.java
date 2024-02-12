package lamball.command;

import lamball.TaskList;

public class UnMarkCommand extends Command {
    private int idx;

    public UnMarkCommand(int idx, TaskList tasks) {
        super(tasks);
        this.idx = idx;
    }

    @Override
    public boolean run() {
        taskList.unMark(idx);
        return true;
    }
}