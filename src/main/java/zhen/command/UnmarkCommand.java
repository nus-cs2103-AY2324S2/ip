package zhen.command;

import zhen.Storage;
import zhen.TaskList;
import zhen.Ui;
/**
 * Represents the command of unmarking a task with a particular index.
 */
public class UnmarkCommand extends Command {
    private int unmarkIndex;
    /**
     * Constructs a command to unmark the task with a particular index in the task list.
     *
     * @param index The index of the task that will be unmarked.
     */
    public UnmarkCommand(int index) {
        this.unmarkIndex = index;
    }
    /**
     * Executes the command of unmarking a task in the task list.
     *
     * @param taskList List of tasks that the command works on.
     * @param ui UI to manage the interaction with users.
     * @param storage To store the product of execution locally.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (unmarkIndex > taskList.getTaskCount() || unmarkIndex < 1) {
            throw new IndexOutOfBoundsException("Please only input index shown in the list");
        }
        String unmarkedTaskInfo = taskList.unmark(unmarkIndex);
        String replyMessage = "OK, I've marked this task as not done yet:\n  " + unmarkedTaskInfo;
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
