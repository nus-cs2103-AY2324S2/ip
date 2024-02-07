package ellie.command;

import ellie.TaskList;

public class MarkUnmarkCommand extends Command {

    protected boolean isMark;
    protected int index;

    /**
     * Constructs a MarkUnmarkCommand object.
     *
     * @param isMark True if the command is to mark a task, otherwise false.
     * @param index The index of the task to be marked or unmarked.
     */
    public MarkUnmarkCommand(boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    /**
     * Marks or unmarks the task in the task list.
     *
     * @param tasklist The TaskList to be operated on by the command.
     */
    public void run(TaskList tasklist) {
        if (isMark) {
            tasklist.markTaskIndex(index);
        } else {
            tasklist.unmarkTaskIndex(index);
        }
    }




}
