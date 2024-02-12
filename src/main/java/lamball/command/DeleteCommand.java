package lamball.command;

import lamball.TaskList;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx, TaskList tasks) {
        super(tasks);
        this.idx = idx;
    }

    @Override
    public boolean run() {
        taskList.deleteFromList(idx);
        return true;
    }
}