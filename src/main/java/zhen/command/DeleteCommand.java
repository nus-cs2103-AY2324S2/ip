package zhen.command;
import zhen.*;
/**
 * Represent the command of deleting a task from the task list
 */
public class DeleteCommand extends Command {
    private int deleteIndex;

    /**
     * Construct a command to delete a task with a particular index from the task list
     *
     * @param index the index of the task that will be deleted
     */
    public DeleteCommand(int index) {
        this.deleteIndex = index;
    }

    /**
     * Execute the command of deleting a task from the task list.
     *
     * @param taskList the List of tasks the command may work on
     * @param ui to manage the interaction with users
     * @param storage to store the product of execution locally.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        if (deleteIndex > taskList.accessNumberTask() || deleteIndex < 1) {
            throw new IndexOutOfBoundsException("Please only input index shown in the list");
        }
        String deletedTaskInfo = taskList.delete(deleteIndex);
        assert taskList.accessNumberTask() >= 0 : "Number of tasks less than zero";
        String replyMessage = "Noted. I've removed this task:\n  "
                + deletedTaskInfo + "\n "
                + "Now you have "
                + taskList.accessNumberTask()
                + " tasks in the list.";
        Ui.print_message(replyMessage);
        storage.writeDisk(taskList.accessList());
        return replyMessage;
    }

    /**
     * Determine whether this command is an exit command
     *
     * @return false, because this is not an exit command
     */
    public boolean isExit() {
        return false;
    }
}
