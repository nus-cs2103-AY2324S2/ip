package felix.command;

import felix.exception.FelixException;
import felix.task.Task;
import felix.utils.Storage;
import felix.utils.TaskList;
import felix.utils.Ui;

/**
 * Class representing command to add a ToDo, Deadline or Event
 */
public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to task list.
     * Prints out corresponding messages to console.
     * Saves updated task list to storage file.
     * @param tasks List of tasks.
     * @param ui Abstraction for user interface.
     * @param storage Abstraction for storage file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        ui.println("Got it. I've added this task:");
        ui.println(task);
        tasks.addTask(task);
        ui.println(String.format("Now you have %d tasks in the list.", tasks.getCount()));
        storage.writeToFile(tasks);
    }
}
