package arc.commands;

import arc.exceptions.ArcException;
import arc.storage.Storage;
import arc.tasks.Task;
import arc.tasks.TaskList;

/**
 * Represents the command for deleting a task from the task list in the Arc application.
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
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws ArcException {
        Task task = tasks.remove(this.taskIndex);

        storage.saveTasks(tasks);

        return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
            task, tasks.size());
    }
}
