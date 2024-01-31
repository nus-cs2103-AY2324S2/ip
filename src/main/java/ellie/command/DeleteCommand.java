package ellie.command;

import ellie.TaskList;

public class DeleteCommand extends Command {

    protected int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void run(TaskList tasklist) {
        tasklist.deleteTaskIndex(index);
        return;
    }



}
