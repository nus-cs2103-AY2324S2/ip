package ellie.command;

import ellie.TaskList;
import ellie.task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    /**
     * The task to be added.
     */
    private Task task;

    /**
     * Constructs an AddCommand object.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list.
     *
     * @param tasklist The TaskList to be operated on by the command.
     */
    public String runAndReturnResponse(TaskList tasklist) {
        String response = tasklist.addTask(task);
        return response;
    };


}
