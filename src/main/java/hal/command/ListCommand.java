package hal.command;

import hal.task.TaskList;

/**
 * The ListCommand class represents a command to list all tasks.
 */
public class ListCommand extends Command {
    private String task;

    /**
     * Constructs a new ListCommand object.
     */
    public ListCommand() {
    }

    /**
     * Executes the list command.
     *
     * @param taskList The TaskList containing the tasks.
     * @return A message containing the list of tasks.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.listTasks();
    }
}
