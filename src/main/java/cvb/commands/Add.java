package cvb.commands;

import cvb.tasks.Task;
import cvb.utils.ResponseConstructor;
import cvb.utils.TaskList;

/**
 * Represents a command to add a task to the task list.
 */
public class Add implements Command {

    private final Task task;

    /**
     * Constructor for the Add command.
     *
     * @param task The task to be added.
     */
    public Add(Task task) {
        this.task = task;
    }

    /**
     * Executes the Add command by adding the task to the task list and displaying a confirmation message.
     *
     * @param taskList The task list to which the task will be added.
     * @param rc       The response constructor for constructing messages.
     */
    public void execute(TaskList taskList, ResponseConstructor rc) {
        taskList.add(task);
        rc.showAdded(task.toString(), taskList.size());
    }

    /**
     * Checks if the Add command is an exit command. Always returns false for Add commands.
     *
     * @return Always false.
     */
    public boolean isExit() {
        return false;
    }
}
