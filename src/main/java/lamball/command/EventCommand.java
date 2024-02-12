package lamball.command;

import lamball.TaskList;

public class EventCommand extends Command {
    private String[] args;
    private boolean isInit;
    public EventCommand(String[] args, TaskList tasks, boolean isInit) {
        super(tasks);
        this.args = args;
        this.isInit = isInit;
    }

    @Override
    public boolean run() {
        taskList.event(args, isInit);
        return true;
    }
}