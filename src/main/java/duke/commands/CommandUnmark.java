package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents the command for marking a task as not done in the Duke application.
 */
public class CommandUnmark extends Command {
    private final Integer taskIndex;

    /**
     * Constructs a new CommandUnmark object with the specified task index to unmark.
     *
     * @param taskIndex The index of the task to unmark.
     */
    public CommandUnmark(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to unmark a task as not done and updates the task list.
     * Saves the updated task list to storage.
     *
     * @param tasks   The task list containing the tasks.
     * @param storage The storage component for saving the task list.
     * @throws DukeException If there is an error while un-marking the task or saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.get(this.taskIndex);
        task.unmark();

        storage.saveTasks(tasks);

        return String.format("OK, I've marked this task as not done yet:\n  %s", task);
    }
}
