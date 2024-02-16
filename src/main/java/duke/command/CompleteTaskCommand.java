package duke.command;

import duke.JamieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to mark a task as complete.
 */
public class CompleteTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a CompleteTaskCommand with the specified task index.
     *
     * @param taskIndex The index of the task to mark as complete.
     */
    public CompleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command by marking the specified task as complete in the task list,
     * saving it to storage, and displaying a message indicating the task has been marked as done.
     *
     * @param tasks   The task list.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the task list.
     * @return A message indicating the task has been marked as done.
     * @throws JamieException If there is an error while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JamieException {
        if (taskIndex <= 0 || taskIndex > tasks.getTasksSize()) {
            throw new JamieException("The task index provided is out of bounds.");
        }
        tasks.completeTask(this.taskIndex - 1);
        try {
            storage.save(tasks);
        } catch (Exception e) { // Assuming storage.save now throws a checked exception that you want to catch.
            throw new JamieException("Failed to save tasks: " + e.getMessage());
        }
        return ui.showMarkAsDoneMessage(tasks.getTask(this.taskIndex - 1));
    }
}
