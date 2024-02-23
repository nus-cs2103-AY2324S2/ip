package duke.command;

import java.io.IOException;

import duke.JamieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to add a task to the task list. Ensures that tasks are
 * added reliably and saved immediately to avoid data loss.
 */
public class AddTaskCommand extends Command {
    private final Task taskToAdd;

    /**
     * Constructs an AddTaskCommand with the specified task to add.
     * Validates that the task is not null to prevent null references in the task list.
     *
     * @param taskToAdd The task to add. Must not be null.
     */
    public AddTaskCommand(Task taskToAdd) {
        if (taskToAdd == null) {
            throw new IllegalArgumentException("Task to add cannot be null.");
        }
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the command by adding the task to the task list, saving it to storage,
     * and displaying a message to indicate the task has been added.
     * If saving fails, wraps the IOException in a JamieException to simplify error handling.
     *
     * @param tasks   The task list.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @return A message indicating the task has been added.
     * @throws JamieException If there is an error while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamieException, IOException {
        tasks.addTask(taskToAdd);
        try {
            storage.save(tasks); // Assuming storage.save handles its own exceptions or throws no checked exceptions
            return ui.showAddTaskMessage(taskToAdd, tasks);
        } catch (IOException e) {
            return "Error saving tasks: " + e.getMessage();
        }
    }
}

