package zhen.command;
import zhen.Storage;
import zhen.TaskList;
import zhen.Ui;
/**
 * Represents the command of deleting a task from the task list
 */
public class DeleteCommand extends Command {
    private int deleteIndex;

    /**
     * Constructs a command to delete a task with a particular index from the task list.
     *
     * @param index The index of the task that will be deleted.
     */
    public DeleteCommand(int index) {
        this.deleteIndex = index;
    }

    /**
     * Executes the command of deleting a task from the task list.
     *
     * @param taskList List of tasks the command may work on.
     * @param ui UI to manage the interaction with users.
     * @param storage To store the product of execution locally.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        if (deleteIndex > taskList.getTaskCount() || deleteIndex < 1) {
            throw new IndexOutOfBoundsException("Please only input index shown in the list");
        }
        String deletedTaskInfo = taskList.delete(deleteIndex);
        assert taskList.getTaskCount() >= 0 : "Number of tasks less than zero";
        String replyMessage = "Noted. I've removed this task:\n  "
                + deletedTaskInfo + "\n "
                + "Now you have "
                + taskList.getTaskCount()
                + " tasks in the list.";
        Ui.print_message(replyMessage);
        storage.writeDisk(taskList.getTasks());
        return replyMessage;
    }

    /**
     * Determines whether this command is an exit command.
     *
     * @return False, because this is not an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
