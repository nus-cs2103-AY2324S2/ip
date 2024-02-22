package james.commands;

import java.io.IOException;

import james.storage.Storage;
import james.tasklist.TaskList;
import james.tasks.Task;
import james.ui.Ui;

/**
 * Represents a command to add a new task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates an AddCommand with the specified task to be added.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command which results in adding the task to the task list,
     * displaying the addition in the UI, and attempting to save the updated task list
     * to the storage.
     *
     * @param tasks   The TaskList to which the task will be added.
     * @param ui      The Ui responsible for interactions with the user.
     * @param storage The Storage where the task list is persisted.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task); // Assume 'task' is already defined in this context
        String result = ui.showTask("Task added:", task, tasks.getSize());
        try {
            storage.save(tasks.getTasks());
        } catch (IOException e) {
            String errorMessage = ui.showError("An error occurred while saving tasks: " + e.getMessage());
            result += "\n" + errorMessage;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Indicates whether the application should terminate after the execution of
     * this command. For AddCommand, it always returns false.
     *
     * @return false as the application should not exit after adding a task.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
