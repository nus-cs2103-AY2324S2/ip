package lamball.command;

import lamball.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(TaskList tasks) {
        super(tasks);
    }

    @Override public boolean run() {
        return false;
    }
}
