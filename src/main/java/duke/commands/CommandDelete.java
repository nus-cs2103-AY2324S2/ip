package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents the command for deleting a task from the task list in the Duke application.
 */
public class CommandDelete extends Command {
    private final Integer taskIndex;

    /**
     * Constructs a new CommandDelete object with the specified task index.
     *
     * @param taskIndex The index of the task to be deleted from the task list.
     */
    public CommandDelete(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the "delete" command, which removes the specified task from the task list,
     * saves the updated task list to storage, and displays a confirmation message to the user.
     *
     * @param tasks   The task list from which the task will be deleted.
     * @param storage The storage component for saving the updated task list.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.remove(this.taskIndex);

        storage.saveTasks(tasks);

        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
            task, tasks.size());
    }
}
