package zhen.command;
import zhen.*;
import zhen.task.*;

/**
 * Represent the command of adding a task to the task list
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Construct a command to add task into the task list
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the command of adding a task to the task list.
     *
     * @param taskList the List of tasks the task will be added to
     * @param ui show the deleting result to the user
     * @param storage store the product of execution locally.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.insert(this.task);
        assert taskList.accessNumberTask() >= 0 : "Number of tasks less than zero";
        String replyMessage = "Got it. I've added this task:\n  "
                + task.toString() + "\n "
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
    @Override
    public boolean isExit() {
        return false;
    }
}
