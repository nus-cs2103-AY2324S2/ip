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
     * Saves updated task list to storage file.
     * Returns String representation of display message.
     * @param tasks List of tasks.
     * @param ui Abstraction for user interface.
     * @param storage Abstraction for storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        int prevSize = tasks.getCount();
        tasks.addTask(task);
        assert prevSize + 1 == tasks.getCount(): "Task has not been added successfully";
        storage.writeToFile(tasks);
        return ui.getAddTaskMessage(task, tasks.getCount());
    }
}
