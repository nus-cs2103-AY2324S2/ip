package zhen.command;

import zhen.Storage;
import zhen.TaskList;
import zhen.Ui;
/**
 * Represents the command of marking a task with a particular index.
 */
public class MarkCommand extends Command {
    private final int markIndex;

    /**
     * Constructs a command to mark the task with a particular index in the task list.
     *
     * @param index The index of the task that will be marked.
     */
    public MarkCommand (int index) {
        this.markIndex = index;
    }
    /**
     * Executes the command of marking a task in the task list.
     *
     * @param taskList List of tasks that the command works on.
     * @param ui UI to manage the interaction with users.
     * @param storage To store the product of execution locally.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        if (markIndex > taskList.getTaskCount() || markIndex < 1) {
            throw new IndexOutOfBoundsException("Please only input index shown in the list");
        }
        String markedTaskInfo = taskList.mark(markIndex);
        String replyMessage = "Nice! I've marked this task as done:\n  " + markedTaskInfo;
        Ui.print_message(replyMessage);
        storage.writeDisk(taskList.getTasks());
        return replyMessage;
    }
    /**
     * Determines whether this command is an exit command.
     *
     * @return False, because this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
