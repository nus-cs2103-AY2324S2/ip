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
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.get(this.taskIndex);
        task.unmark();

        storage.saveTasks(tasks);

        return String.format("OK, I've marked this task as not done yet:\n  %s", task);
    }
}
