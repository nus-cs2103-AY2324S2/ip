package micromanager.commands;

import micromanager.exceptions.DukeException;
import micromanager.storage.Storage;
import micromanager.storage.TaskList;
import micromanager.tasks.Task;

/**
 * Represents a command to update a task in the task list.
 * This class provides methods to construct an update command with the index of the task to be updated
 * and the new task information, and to execute the command by updating the task in the task list.
 */
public class UpdateCommand extends Command {
    private int index;
    private Task newTask;

    /**
     * Constructs an UpdateCommand object.
     *
     * @param index   The index of the task to update.
     * @param newTask The new task to replace the existing task.
     */
    public UpdateCommand(int index, Task newTask) {
        super();
        this.index = index;
        this.newTask = newTask;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.update(index - 1, newTask);

        return "Got it. I've updated this task:\n"
                + "  " + newTask + "\n"
                + String.format("Now you have %d tasks in the list.%n", taskList.size());
    }
}
