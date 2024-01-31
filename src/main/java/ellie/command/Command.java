package ellie.command;

import ellie.TaskList;

public abstract class Command {

    protected boolean isExit = false;

    public abstract void run(TaskList tasklist);

    public boolean isExit() {
        return isExit;
    }

}
