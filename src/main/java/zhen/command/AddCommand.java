package zhen.command;

import zhen.Storage;
import zhen.TaskList;
import zhen.Ui;
import zhen.task.Task;

/**
 * Represents the command of adding a task to the task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs a command to add task into the task list.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command of adding a task to the task list.
     *
     * @param taskList The List of tasks the task will be added to
     * @param ui Shows the deleting result to the user
     * @param storage Stores the product of execution locally.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.insert(this.task);
        assert taskList.getTaskCount() >= 0 : "Number of tasks less than zero";
        String replyMessage = "Got it. I've added this task:\n  "
                + task.toString() + "\n "
                + "Now you have "
                + taskList.getTaskCount()
                + " tasks in the list.";
        Ui.print_message(replyMessage);
        storage.writeDisk(taskList.getTasks());
        return replyMessage;
    }

    /**
     * Determine whether this command is an exit command
     *
     * @return False, because this is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
