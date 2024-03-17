package hal.command;

import hal.task.TaskList;

/**
 * The AddCommand class represents a command to add a task.
 */
public class AddCommand extends Command {
    private String task;

    /**
     * Constructs a new AddCommand object.
     *
     * @param task The task to be added.
     */
    public AddCommand(String task) {
        this.task = task;
    }

    /**
     * Executes the add command.
     *
     * @param taskList The TaskList containing the tasks.
     * @return A message indicating the result of the add operation.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.addTask(task);
    }
}
