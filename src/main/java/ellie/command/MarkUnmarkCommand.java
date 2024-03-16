package ellie.command;

import ellie.TaskList;

/**
 * Represents a command to mark or unmark a task.
 */
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
     * Marks or unmarks a task in the provided TaskList. Returns response to be displayed.
     *
     * @param tasklist The TaskList to be operated on by the command.
     */
    public String runAndReturnResponse(TaskList tasklist) {
        String response;
        if (isMark) {
            response = tasklist.markTaskIndex(index);
        } else {
            response = tasklist.unmarkTaskIndex(index);
        }
        return response;
    }

}
