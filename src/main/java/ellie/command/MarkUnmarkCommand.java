package ellie.command;

import ellie.TaskList;

public class MarkUnmarkCommand extends Command {

    protected boolean isMark;
    protected int index;

    public MarkUnmarkCommand(boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    public void run(TaskList tasklist) {
        if (isMark) {
            tasklist.markTaskIndex(index);
        } else {
            tasklist.unmarkTaskIndex(index);
        }
    }




}
