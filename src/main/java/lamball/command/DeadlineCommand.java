package lamball.command;

import lamball.TaskList;

public class DeadlineCommand extends Command {
    private String[] args;
    private boolean isInit;
    public DeadlineCommand(String[] args, TaskList tasks, boolean isInit) {
        super(tasks);
        this.args = args;
        this.isInit = isInit;
    }

    @Override
    public boolean run() {
        taskList.deadline(args, isInit);
        return true;
    }
}